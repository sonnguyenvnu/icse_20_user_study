public Map<String,Object> getDiffMap(){
  if (diffMap != null) {
    return diffMap;
  }
 else {
    if (StringUtils.isNotBlank(diffJson)) {
      JSONObject jsonObject;
      try {
        jsonObject=JSONObject.fromObject(diffJson);
        Map<String,Object> map=transferMapByJson(jsonObject);
        diffMap=map;
      }
 catch (      Exception e) {
        logger.error(e.getMessage());
      }
    }
  }
  return diffMap;
}
