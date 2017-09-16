package com.xiaoge.graphics.apngsequence.apngdrawable;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


/**
 * reference : https://github.com/kris520/ApngDrawable
 */
public class ApngImageLoadingListener {

    private ApngPlayListener playListener = null; // 播放监听

    public ApngImageLoadingListener(ApngPlayListener playListener) {
        this.playListener = playListener;

    }

    public void onLoadingComplete(String imageUri, ImageView imageView, Drawable loadedImage) {
        if (loadedImage instanceof ApngDrawable) {

            if(playListener != null) {
                ((ApngDrawable) loadedImage).setPlayListener(playListener);
            }
//            ((ApngDrawable)loadedImage).start();
        }
    }

    public void onLoadFailed(String imageUri, ImageView imageView) {

    }
}
