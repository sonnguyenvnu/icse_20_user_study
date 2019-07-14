public void setMaxSelectCount(int count){
  if (mSelectedView.size() > count) {
    Log.w(TAG,"you has already select more than " + count + " views , so it will be clear .");
    mSelectedView.clear();
  }
  mSelectedMax=count;
}
