@Override public void updateAppKey(long appId){
  appDao.updateAppKey(appId,AppKeyUtil.genSecretKey(appId));
}
