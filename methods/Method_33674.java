@Override protected void loadData(){
  if (mIsPrepared && isLoadBanner) {
    onResume();
  }
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.srlWan.setRefreshing(true);
  bindingView.srlWan.postDelayed(this::getWanAndroidBanner,500);
}
