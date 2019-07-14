@Override public AppDesc getByAppId(Long appId){
  Assert.isTrue(appId > 0);
  AppDesc appDesc=null;
  try {
    appDesc=appDao.getAppDescById(appId);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return appDesc;
}
