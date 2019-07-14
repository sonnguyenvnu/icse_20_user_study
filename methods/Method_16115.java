@Override public void use(String dataSourceId){
  getUsedHistoryQueue().addLast(dataSourceId);
  if (logger.isDebugEnabled()) {
    logger.debug("try use database : {}",dataSourceId);
  }
}
