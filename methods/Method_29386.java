@Override public boolean cleanAppData(long appId,AppUser appUser){
  try {
    AppDesc appDesc=appDao.getAppDescById(appId);
    if (appDesc == null) {
      return false;
    }
    if (TypeUtil.isRedisType(appDesc.getType())) {
      return redisCenter.cleanAppData(appDesc,appUser);
    }
 else {
      return false;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}
