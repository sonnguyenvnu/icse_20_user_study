public void onFinalImageSet(long id,@ImageOrigin int imageOrigin,@Nullable ImageInfo imageInfo,@Nullable Drawable drawable){
  if (drawable instanceof AnimatedDrawable2) {
    ((AnimatedDrawable2)drawable).start();
  }
}
