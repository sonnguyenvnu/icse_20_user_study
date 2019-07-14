@Override public void reset(){
  getUsedHistoryQueue().clear();
  if (logger.isDebugEnabled()) {
    logger.debug("reset datasource history");
  }
}
