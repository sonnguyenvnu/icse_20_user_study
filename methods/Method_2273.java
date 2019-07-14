@Override public void onFinalImageSet(long id,@ImageOrigin int imageOrigin,@Nullable ImageInfo imageInfo,@Nullable Drawable drawable){
  String stringId=toStringId(id);
  if (mImageOriginListener != null) {
    mImageOriginListener.onImageLoaded(stringId,imageOrigin,true,"ControllerListenerWrapper");
  }
  Animatable animatable=drawable instanceof Animatable ? (Animatable)drawable : null;
  mControllerListener.onFinalImageSet(stringId,imageInfo,animatable);
}
