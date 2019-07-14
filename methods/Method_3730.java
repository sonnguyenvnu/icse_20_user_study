private boolean isRefreshPending(){
  return mRequestedGeneration != mDisplayedGeneration;
}
