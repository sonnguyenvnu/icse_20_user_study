@Override public void saveOrUpdateClientVersion(long appId,String appClientIp,String clientVersion){
  try {
    AppClientVersion appClientVersion=new AppClientVersion();
    appClientVersion.setAppId(appId);
    appClientVersion.setClientIp(appClientIp);
    appClientVersion.setClientVersion(clientVersion);
    appClientVersion.setReportTime(new Date());
    appClientVersionDao.saveOrUpdateClientVersion(appClientVersion);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
