package com.example.camera;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CameraFragment.OnCameraFragmentInteractionListener, PhotoFragment.OnListFragmentInteractionListener {

    // SharedPreferences constants
    private static final String SHARED_PREFS = "SP";
    private static final String SP_PHOTOS = "SP_PHOTOS";
    // ---
    private static ArrayList<Bitmap> mPhotosList;



    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting ...");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mPhotosList = loadData();
    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment(), "Camera");
        adapter.addFragment(new PhotoFragment(), "Photos");

        viewPager.setAdapter(adapter);
    }

    public ArrayList<Bitmap> loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SP_PHOTOS,"");
        Type type = new TypeToken<ArrayList<Bitmap>>(){}.getType();
        ArrayList<Bitmap> photosList = gson.fromJson(json, type);

        if(photosList == null){
            photosList = new ArrayList<Bitmap>();
        }

        return photosList;
    }

    public void addPosition(Bitmap photo){

        ArrayList<Bitmap> photosList = loadData();
        photosList.add(photo);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(photosList);
        editor.putString(SP_PHOTOS,json);
        editor.apply();
    }

    @Override
    public ArrayList<Bitmap> onListFragmentInteraction() {
        return loadData();
    }

    @Override
    public void onCameraFragmentInteraction(Bitmap bitmap) {
        addPosition(bitmap);
    }
}