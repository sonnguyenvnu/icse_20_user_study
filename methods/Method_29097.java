@Override public List<AppClientValueDistriSimple> getAppValueDistriList(long appId,long startTime,long endTime){
  try {
    return appClientValueStatDao.getAppValueDistriList(appId,startTime,endTime);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
