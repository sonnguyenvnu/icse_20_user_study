@Override protected void onInvisible(){
  if (mIsPrepared && isLoadBanner) {
    onPause();
  }
}
