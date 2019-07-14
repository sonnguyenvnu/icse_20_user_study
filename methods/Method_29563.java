/** 
 * ??hostport
 * @param instanceAlertSpecialList
 */
private void fillinstanceHostPort(List<InstanceAlertConfig> instanceAlertSpecialList){
  if (CollectionUtils.isEmpty(instanceAlertSpecialList)) {
    return;
  }
  for (  InstanceAlertConfig instanceAlertConfig : instanceAlertSpecialList) {
    long instanceId=instanceAlertConfig.getInstanceId();
    InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
    if (instanceInfo == null) {
      continue;
    }
    instanceAlertConfig.setInstanceInfo(instanceInfo);
  }
}
