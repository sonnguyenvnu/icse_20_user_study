@Override public void onFinalImageSet(String id,@Nullable Object imageInfo,@Nullable Animatable animatable){
  mFinalImageSetTimeMs=System.currentTimeMillis();
  if (mImageLoadingTimeListener != null) {
    mImageLoadingTimeListener.onFinalImageSet(mFinalImageSetTimeMs - mRequestSubmitTimeMs);
  }
}
