package com.xiaoge.graphics.apngsequence.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;


import com.xiaoge.graphics.apngsequence.apngdrawable.ApngDrawable;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngImageLoadingListener;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngImageUtils;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by zhanglei on 17-7-18.
 */

public class ApngHelper {

    public static void loadAnimatedPng(ImageView view, String resource, @Nullable ApngImageLoadingListener listener) {
        ApngImageUtils.Scheme scheme = ApngImageUtils.Scheme.FILE;
        // load image
        String url = scheme.wrap(resource);
        ApngLoader.loadImageFile(url, view, listener);
    }

    public static boolean isAPNG(@NonNull File file) {
        return file.exists() && file.isFile() && ApngDrawable.isApng(file);
    }

    public static boolean isAPNG(@NonNull InputStream inputStream) {
        try {
            return inputStream.available() > 0 && ApngDrawable.isApng(inputStream);
        } catch (Exception ignore) {

        }
        return false;
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (Exception ignore) {
            return null;
        }
    }

}
