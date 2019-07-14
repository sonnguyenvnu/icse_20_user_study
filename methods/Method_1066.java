private boolean shouldRetryOnTap(){
  return mHasFetchFailed && mRetryManager != null && mRetryManager.shouldRetryOnTap();
}
