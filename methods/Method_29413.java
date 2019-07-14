/** 
 * ????????????????
 * @param specialInstanceAlertConfigList
 * @param instanceAlertConfig
 * @param instanceInfo
 * @return
 */
private InstanceAlertConfig filterSpecial(List<InstanceAlertConfig> specialInstanceAlertConfigList,InstanceAlertConfig instanceAlertConfig,InstanceInfo instanceInfo){
  if (CollectionUtils.isEmpty(specialInstanceAlertConfigList)) {
    return instanceAlertConfig;
  }
  for (  InstanceAlertConfig specialInstanceAlertConfig : specialInstanceAlertConfigList) {
    String specialAlertConfig=specialInstanceAlertConfig.getAlertConfig();
    long instanceId=specialInstanceAlertConfig.getInstanceId();
    if (instanceAlertConfig.getAlertConfig().equals(specialAlertConfig) && instanceInfo.getId() == instanceId) {
      return specialInstanceAlertConfig;
    }
  }
  return instanceAlertConfig;
}
