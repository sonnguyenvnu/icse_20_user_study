@Override public void onFinalImageSet(long finalImageTimeMs){
  mFinalImageTimeMs=finalImageTimeMs;
  invalidateSelf();
}
