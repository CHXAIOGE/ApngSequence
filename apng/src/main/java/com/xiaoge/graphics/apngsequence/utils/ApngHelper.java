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
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.xiaoge.graphics.apngsequence.apngdrawable.ApngImageUtils.getFileCachePath;

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

    public static void loadAnimatedPng(ImageView view, InputStream stream, @Nullable ApngImageLoadingListener listener) {
        File destination;
        try {
            String md5 = getMD5(stream);
            stream.reset();
            destination = new File(getFileCachePath(ApngLoader.getAppContext(), md5));
            if (!destination.exists()) {
                ApngImageUtils.copyInputStreamToFile(stream, destination);
            }
            loadAnimatedPng(view, destination.getAbsolutePath(), listener);
        } catch (Exception e) {
            if(listener != null) {
                listener.onLoadFailed(null, view);
            }
        }
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

    private static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        for (byte mdbyte : mdbytes) {
            md5.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }
}
