@Override public void onProgress(final int progress){
  if (mProgressIndicator != null) {
    mProgressIndicator.onProgress(progress);
  }
  if (mUserCallback != null) {
    mUserCallback.onProgress(progress);
  }
}
