@Override public List<AppClientCostTimeTotalStat> getAppClientCommandTotalStat(Long appId,String command,long startTime,long endTime){
  try {
    return appClientCostTimeTotalStatDao.getAppClientCommandStat(appId,command,startTime,endTime);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
