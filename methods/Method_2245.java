@Override public void onFailure(long id,@Nullable Drawable error,@Nullable Throwable throwable){
  if (mImageListener != null) {
    mImageListener.onFailure(id,error,throwable);
  }
  if (mOtherListeners != null) {
    mOtherListeners.onFailure(id,error,throwable);
  }
}
