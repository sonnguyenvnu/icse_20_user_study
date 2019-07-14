public void refresh(){
  mSortedList.beginBatchedUpdates();
  mSortedList.clear();
  mSortedList.addAll(mList);
  mSortedList.endBatchedUpdates();
}
