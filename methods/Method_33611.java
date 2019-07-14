@Override protected void loadData(){
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  loadAndroidData();
}
