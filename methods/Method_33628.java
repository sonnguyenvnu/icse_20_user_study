@Override protected void loadData(){
  if (!mIsVisible || !isPrepared || !isFirst) {
    return;
  }
  loadWelfareData();
}
