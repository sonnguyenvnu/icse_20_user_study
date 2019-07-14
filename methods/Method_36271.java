@Override public void onCacheMiss(final int imageType,final File image){
  mCurrentImageFile=image;
  mTempImages.add(image);
  doShowImage(imageType,image);
  if (mUserCallback != null) {
    mUserCallback.onCacheMiss(imageType,image);
  }
}
