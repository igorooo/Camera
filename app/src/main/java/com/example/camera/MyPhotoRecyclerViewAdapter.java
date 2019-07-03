package com.example.camera;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.camera.PhotoFragment.OnListFragmentInteractionListener;
import com.example.camera.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class MyPhotoRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotoRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyPhotoRecyclerView";

    private ArrayList<Bitmap> mPhotos;
    private Context mContext;

    public MyPhotoRecyclerViewAdapter(ArrayList<Bitmap> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called ...");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photo, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    // @TODO Crush  here, SIGSEGV, probably problem with BITMAP, need to go for Glide

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called ...");

        Bitmap photo = mPhotos.get(position);

        ImageView imageView = holder.imgView;

        Log.d(TAG, "onBindViewHolder: exiting ... ");

        imageView.setImageBitmap(photo);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgViewPosition);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
