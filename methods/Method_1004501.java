public void clean(final StorageConfig config,final LogManager.DeleteHook hook){
  logManager.deleteExpiredSegments(config.getSMTRetentionMs(),hook);
}
