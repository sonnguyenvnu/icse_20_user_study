/** 
 * ??instance??????
 * @param instanceAlertConfig
 * @param instanceInfo
 * @param currentValue
 * @param unit
 * @return
 */
protected String genInstanceAlertText(InstanceAlertConfig instanceAlertConfig,InstanceInfo instanceInfo,String currentValue,String unit){
  String configKey=instanceAlertConfig.getAlertConfig();
  String configValue=instanceAlertConfig.getAlertValue();
  String compareTypeInfo=InstanceAlertCompareTypeEnum.getInstanceAlertCompareTypeEnum(instanceAlertConfig.getCompareType()).getInfo();
  StringBuilder alertText=new StringBuilder();
  alertText.append(instanceInfo.getHostPort());
  alertText.append(",???:").append(configKey);
  alertText.append("=").append(currentValue).append(unit).append(",");
  alertText.append(compareTypeInfo);
  alertText.append("????:").append(configValue).append(unit);
  return alertText.toString();
}
