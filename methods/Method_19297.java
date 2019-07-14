private synchronized void closeCurrentBatch(boolean isDataChanged,ChangeSetCompleteCallback changeSetCompleteCallback){
  if (mCurrentBatch == null) {
    mCurrentBatch=new AsyncBatch(mCommitPolicy);
  }
  mCurrentBatch.mIsDataChanged=isDataChanged;
  mCurrentBatch.mChangeSetCompleteCallback=changeSetCompleteCallback;
  mAsyncBatches.addLast(mCurrentBatch);
  mHasAsyncBatchesToCheck.set(true);
  mCurrentBatch=null;
}
