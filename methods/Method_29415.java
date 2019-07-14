@Override public String showInstanceRecentLog(int instanceId,int maxLineNum){
  Assert.isTrue(instanceId > 0L);
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  Assert.isTrue(instanceInfo != null);
  try {
    return machineCenter.showInstanceRecentLog(instanceInfo,maxLineNum);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return "";
  }
}
