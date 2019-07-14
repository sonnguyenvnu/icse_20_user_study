/** 
 * ???????key-value
 * @return
 */
private Map<String,String> getConfigMap(){
  Map<String,String> configMap=new LinkedHashMap<String,String>();
  List<SystemConfig> systemConfigList=getConfigList(1);
  for (  SystemConfig systemConfig : systemConfigList) {
    configMap.put(systemConfig.getConfigKey(),systemConfig.getConfigValue());
  }
  return configMap;
}
