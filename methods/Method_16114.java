@Override public void useLast(){
  if (getUsedHistoryQueue().isEmpty()) {
    return;
  }
  getUsedHistoryQueue().removeLast();
  if (logger.isDebugEnabled()) {
    String current=currentDatabase();
    if (null != current) {
      logger.debug("try use database : {}",currentDatabase());
    }
 else {
      logger.debug("try use last default database");
    }
  }
}
