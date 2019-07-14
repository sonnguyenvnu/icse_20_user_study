private boolean isExist(long appId){
  List<InstanceInfo> instanceInfos=instanceDao.getInstListByAppId(appId);
  if (instanceInfos != null && instanceInfos.size() > 0) {
    logger.error("appId={} instances is exist , instanceInfos={}",appId,instanceInfos);
    return false;
  }
  return true;
}
