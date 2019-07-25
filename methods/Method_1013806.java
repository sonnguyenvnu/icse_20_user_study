@Override public void destroy() throws Exception {
  logger.info("[destroy]{}",this);
  ReplicationStore currentReplicationStore=getCurrent();
  if (currentReplicationStore != null) {
    try {
      currentReplicationStore.destroy();
    }
 catch (    Throwable th) {
      logger.error("[destroy]",th);
    }
  }
  FileUtils.recursiveDelete(this.baseDir);
}
