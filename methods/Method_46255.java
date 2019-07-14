/** 
 * ????????
 */
protected void checkLeak(){
  if (CommonUtils.isNotEmpty(urlConnectionMap)) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Bolt client transport maybe leak. {}",urlConnectionMap);
    }
    urlConnectionMap.clear();
  }
  if (CommonUtils.isNotEmpty(connectionRefCounter)) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Bolt client transport maybe leak. {}",connectionRefCounter);
    }
    connectionRefCounter.clear();
  }
}
