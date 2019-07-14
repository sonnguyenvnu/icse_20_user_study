@Override protected void loadData(){
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.srlWan.setRefreshing(true);
  bindingView.srlWan.postDelayed(this::getCollectList,150);
}
