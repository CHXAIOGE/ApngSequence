package com.xiaoge.graphics.apngsequence.apngdrawable;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * reference : https://github.com/kris520/ApngDrawable
 */
public class ApngInvalidationHandler extends Handler {
    private final WeakReference<ApngDrawable> mDrawableRef;

    public ApngInvalidationHandler(ApngDrawable apngDrawable) {
        super(Looper.getMainLooper());
        mDrawableRef = new WeakReference<>(apngDrawable);
    }

    @Override
    public void handleMessage(Message msg) {
        final ApngDrawable apngDrawable = mDrawableRef.get();
        if (apngDrawable != null) {
            apngDrawable.invalidateSelf();
        }
    }
}
