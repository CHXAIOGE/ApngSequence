# ApngSequence
Apng support for android


fork form: https://github.com/kris520/ApngDrawable

this is a module library for lib 'animategraphics'
or you can just use it as well.


Usage:

  // Init ApngLoader
	ApngLoader.init(this);

	// Load a apng to imageView
	// ImageView scaleType support CENTER_INSIDE/CENTER_CROP/FIT_XY, and default FIT_XY
	File imageFile;
	ApngHelper.loadAnimatedPng(view, file.getAbsolutePath(), new ApngImageLoadingListener(null) {
            public void onLoadingComplete(String imageUri, ImageView imageView, Drawable loadedImage) {
                super.onLoadingComplete(imageUri, imageView, loadedImage);
                Log.d(TAG, "onLoadingComplete");
                view.setImageDrawable(loadedImage);
            }

            public void onLoadFailed(String imageUri, ImageView imageView) {
                Log.d(TAG, "onLoadFailed");
            }
        });

目前Apng支持不完整，在部分特殊情况下，drawable渲染的时候可能出现上一帧部分残留。
原因是canvas绘制时cliprect导致

