private synchronized boolean increaseAndCheckIfLast(){
  return ++mFinishedDataSources == mDataSources.length;
}
