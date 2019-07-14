@Override protected void loadData(){
  DebugUtil.error("-----loadData");
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.srlWan.setRefreshing(true);
  bindingView.srlWan.postDelayed(this::getBook,150);
  DebugUtil.error("-----setRefreshing");
}
