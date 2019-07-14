@Override protected void loadData(){
  DebugUtil.error("-----loadData");
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.srlWan.setRefreshing(true);
  bindingView.srlWan.postDelayed(this::getHotFilm,150);
  DebugUtil.error("-----setRefreshing");
}
