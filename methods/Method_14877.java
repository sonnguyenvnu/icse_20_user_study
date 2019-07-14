@Override public void initData(){
  super.initData();
  if (isCurrentUserCorrect() == false) {
    onDataChanged();
  }
}
