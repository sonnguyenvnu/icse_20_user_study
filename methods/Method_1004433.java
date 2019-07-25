public void clean(){
  logManager.deleteExpiredSegments(config.getMessageLogRetentionMs());
}
