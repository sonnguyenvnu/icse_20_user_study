@Override public List<AppClientExceptionStat> getAppExceptionList(Long appId,long startTime,long endTime,int type,String clientIp,Page page){
  try {
    return appClientExceptionStatDao.getAppExceptionList(appId,startTime,endTime,type,clientIp,page);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
