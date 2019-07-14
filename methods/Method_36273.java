@Override public void onSuccess(final File image){
  if (mUserCallback != null) {
    mUserCallback.onSuccess(image);
  }
}
