package com.mrcarefree.laravel_android;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageHelper {
    public static void getImage(ImageView imageView, String image_url){
        Picasso.get().load(image_url)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("Picasso", "Failed to load image " + e.getMessage());
                    }
                });
    }
}
