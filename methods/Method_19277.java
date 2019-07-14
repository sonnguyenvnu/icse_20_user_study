@GuardedBy("this") private void registerAsyncInsert(AsyncInsertOperation operation){
  addToCurrentBatch(operation);
  final ComponentTreeHolder holder=operation.mHolder;
  holder.setNewLayoutReadyListener(mAsyncLayoutReadyListener);
  if (isMeasured()) {
    computeLayoutAsync(holder);
  }
}
