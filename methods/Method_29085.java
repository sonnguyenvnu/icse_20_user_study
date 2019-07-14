@Override public List<String> getAppDistinctCommand(Long appId,long startTime,long endTime){
  try {
    return appClientCostTimeTotalStatDao.getAppDistinctCommand(appId,startTime,endTime);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
