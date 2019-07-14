public Map<String,Object> getInfoMap(){
  if (infoMap != null) {
    return infoMap;
  }
 else {
    if (StringUtils.isNotBlank(infoJson)) {
      JSONObject jsonObject;
      try {
        jsonObject=JSONObject.fromObject(infoJson);
        Map<String,Object> map=transferMapByJson(jsonObject);
        infoMap=map;
      }
 catch (      Exception e) {
        logger.error(e.getMessage());
      }
    }
  }
  return infoMap;
}
