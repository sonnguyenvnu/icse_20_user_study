@Override public void onCacheHit(final int imageType,File image){
  mCurrentImageFile=image;
  doShowImage(imageType,image);
  if (mUserCallback != null) {
    mUserCallback.onCacheHit(imageType,image);
  }
}
