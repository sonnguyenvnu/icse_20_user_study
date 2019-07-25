public CacheManagerTask execute(final CacheManagerTask pTask){
  pTask.execute();
  mPendingTasks.add(pTask);
  return pTask;
}
