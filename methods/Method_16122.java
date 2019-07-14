@Override public void useDefault(){
  getUsedHistoryQueue().addLast(DEFAULT_DATASOURCE_ID);
  if (logger.isDebugEnabled()) {
    logger.debug("try use default datasource");
  }
}
