@Override public boolean isRun(final long appId,final String ip,final int port){
  AppDesc appDesc=appDao.getAppDescById(appId);
  return isRun(ip,port,appDesc.getPassword());
}
