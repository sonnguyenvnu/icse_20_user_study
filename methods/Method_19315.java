@GuardedBy("this") private void updateAsyncInsertOperations(){
  for (  AsyncBatch batch : mAsyncBatches) {
    updateBatch(batch);
  }
  if (mCurrentBatch != null) {
    updateBatch(mCurrentBatch);
  }
}
