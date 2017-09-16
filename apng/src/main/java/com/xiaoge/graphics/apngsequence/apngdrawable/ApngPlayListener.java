package com.xiaoge.graphics.apngsequence.apngdrawable;

/**
 * reference : https://github.com/kris520/ApngDrawable
 */
public interface ApngPlayListener {
    void onAnimationStart(ApngDrawable drawable);
    void onAnimationEnd(ApngDrawable drawable);
    void onAnimationRepeat(ApngDrawable drawable);
}
