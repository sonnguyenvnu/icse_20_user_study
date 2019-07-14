@Override public void useLast(){
  if (getUsedHistoryQueue().isEmpty()) {
    return;
  }
  getUsedHistoryQueue().removeLast();
  if (logger.isDebugEnabled()) {
    String current=currentDataSourceId();
    if (null != current) {
      logger.debug("try use last datasource : {}",currentDataSourceId());
    }
 else {
      logger.debug("try use last default datasource");
    }
  }
}
