@GuardedBy("this") private void updateBatch(AsyncBatch batch){
  for (  AsyncOperation operation : batch.mOperations) {
    if (!(operation instanceof AsyncInsertOperation)) {
      continue;
    }
    final ComponentTreeHolder holder=((AsyncInsertOperation)operation).mHolder;
    computeLayoutAsync(holder);
  }
}
