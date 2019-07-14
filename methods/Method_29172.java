public void setInfoMap(Map<String,Object> infoMap){
  if (infoJson == null) {
    JSONObject jsonObject;
    try {
      jsonObject=JSONObject.fromObject(infoMap);
      infoJson=jsonObject.toString();
    }
 catch (    Exception e) {
      logger.error(e.getMessage());
    }
  }
  this.infoMap=infoMap;
}
