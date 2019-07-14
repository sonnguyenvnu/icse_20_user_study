@Override public void onFinalImageSet(long id,@ImageOrigin int imageOrigin,@Nullable ImageInfo imageInfo,@Nullable Drawable drawable){
  if (mImageListener != null) {
    mImageListener.onFinalImageSet(id,imageOrigin,imageInfo,drawable);
  }
  if (mOtherListeners != null) {
    mOtherListeners.onFinalImageSet(id,imageOrigin,imageInfo,drawable);
  }
}
