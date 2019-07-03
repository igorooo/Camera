package com.example.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camera.dummy.DummyContent;
import com.example.camera.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PhotoFragment extends Fragment {

    private static final String TAG = "PhotoFragment";

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<Bitmap> mPhotos = new ArrayList<>();


    public PhotoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        Log.d(TAG, "onCreateView: starting ...");
        loadPhotos("onCreateView");
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), mColumnCount));
        recyclerView.setAdapter(new MyPhotoRecyclerViewAdapter(mPhotos, (Context) mListener));

        Log.d(TAG, "onCreateView: finishing ...");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: starting ...");
        loadPhotos("onResume");
    }

    private void loadPhotos(String M_TAG){
        try{
            mPhotos = mListener.onListFragmentInteraction();
        }
        catch (NullPointerException e){
            Log.d(TAG, M_TAG + ": NullPointerException: mListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    public interface OnListFragmentInteractionListener {
        ArrayList<Bitmap> onListFragmentInteraction();
    }
}
