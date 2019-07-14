private static boolean isBatchReady(AsyncBatch batch){
  if (batch.mCommitPolicy == CommitPolicy.IMMEDIATE) {
    return true;
  }
  for (int i=0, size=batch.mOperations.size(); i < size; i++) {
    final AsyncOperation operation=batch.mOperations.get(i);
    if (operation instanceof AsyncInsertOperation && !((AsyncInsertOperation)operation).mHolder.hasCompletedLatestLayout()) {
      return false;
    }
  }
  return true;
}
