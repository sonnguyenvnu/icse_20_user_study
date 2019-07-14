private void notifyError(ApiError error){
  if (!mHasError) {
    mHasError=true;
    getListener().onLoadError(getRequestCode(),error);
  }
}
