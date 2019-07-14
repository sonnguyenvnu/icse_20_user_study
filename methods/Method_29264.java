@Override public List<InstanceSlowLog> getInstanceSlowLogByAppId(long appId,Date startDate,Date endDate){
  try {
    return instanceSlowLogDao.search(appId,startDate,endDate);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
