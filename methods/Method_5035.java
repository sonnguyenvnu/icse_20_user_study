@Override public void download() throws InterruptedException, IOException {
  priorityTaskManager.add(C.PRIORITY_DOWNLOAD);
  try {
    CacheUtil.cache(dataSpec,cache,cacheKeyFactory,dataSource,new byte[BUFFER_SIZE_BYTES],priorityTaskManager,C.PRIORITY_DOWNLOAD,cachingCounters,isCanceled,true);
  }
  finally {
    priorityTaskManager.remove(C.PRIORITY_DOWNLOAD);
  }
}
