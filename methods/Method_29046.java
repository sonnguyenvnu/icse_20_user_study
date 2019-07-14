/** 
 * ???????????
 * @param redisInfo
 * @param attribute
 * @return
 */
protected static Object getValueFromRedisInfo(StandardStats standardStats,String attribute){
  if (standardStats == null) {
    return null;
  }
  Map<String,Object> infoMap=JsonUtil.fromJson(standardStats.getInfoJson(),Map.class);
  if (MapUtils.isEmpty(infoMap)) {
    return null;
  }
  for (  Entry<String,Object> entry : infoMap.entrySet()) {
    Object object=entry.getValue();
    if (!(object instanceof Map)) {
      continue;
    }
    Map<String,Object> sectionInfoMap=(Map<String,Object>)object;
    if (sectionInfoMap != null && sectionInfoMap.containsKey(attribute)) {
      return MapUtils.getObject(sectionInfoMap,attribute);
    }
  }
  return null;
}
