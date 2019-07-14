void reset(){
  recycleUpdateOpsAndClearList(mPendingUpdates);
  recycleUpdateOpsAndClearList(mPostponedList);
  mExistingUpdateTypes=0;
}
