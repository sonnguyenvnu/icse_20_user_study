public void clean(){
  logManager.deleteExpiredSegments(config.getLogRetentionMs());
}
