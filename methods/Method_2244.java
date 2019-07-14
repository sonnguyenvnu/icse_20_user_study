@Override public void onIntermediateImageSet(long id,@Nullable ImageInfo imageInfo){
  if (mImageListener != null) {
    mImageListener.onIntermediateImageSet(id,imageInfo);
  }
  if (mOtherListeners != null) {
    mOtherListeners.onIntermediateImageSet(id,imageInfo);
  }
}
