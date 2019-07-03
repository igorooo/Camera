package com.example.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class CameraFragment extends Fragment {

    private static final String TAG = "CameraFragment";

    private ImageButton cameraButton;
    private ImageView imgView;

    private OnCameraFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: starting ...");
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        cameraButton = (ImageButton) view.findViewById(R.id.B1);
        imgView = (ImageView) view.findViewById(R.id.img1);



        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchCamera();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (OnCameraFragmentInteractionListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnCameraFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imgView.setImageBitmap(bitmap);

        mListener.onCameraFragmentInteraction(bitmap);
    }

    private void lunchCamera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public interface OnCameraFragmentInteractionListener{
        public void onCameraFragmentInteraction(Bitmap bitmap);
    }
}
