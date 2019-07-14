/** 
 * merge data
 * @param userData
 * @param configData
 * @return
 */
private ComposeUserData composeUserAndConfigData(UserData userData,ConfigData configData){
  ComposeUserData result=new ComposeUserData();
  Map<String,List<ProviderInfo>> zoneData=new HashMap<String,List<ProviderInfo>>();
  if (userData == null) {
    return result;
  }
 else {
    result.setLocalZone(userData.getLocalZone());
    final Map<String,List<String>> listZoneData=userData.getZoneData();
    final String[] configDatas=StringUtils.split(configData == null ? StringUtils.EMPTY : configData.getData(),CONFIG_SEPARATOR);
    final List<String> attrData=Arrays.asList(configDatas);
    for (    String key : listZoneData.keySet()) {
      final List<ProviderInfo> providerInfos=mergeProviderInfo(listZoneData.get(key),attrData);
      zoneData.put(key,providerInfos);
    }
    result.setZoneData(zoneData);
  }
  return result;
}
