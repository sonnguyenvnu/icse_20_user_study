/** 
 * ????JsonObject
 * @param jsonObject
 * @return
 */
private Map<String,Object> transferMapByJson(JSONObject jsonObject){
  Map<String,Object> map=new LinkedHashMap<String,Object>();
  for (Iterator keys=jsonObject.keys(); keys.hasNext(); ) {
    String key=String.valueOf(keys.next());
    Object value=jsonObject.get(key);
    if (value instanceof JSONObject) {
      JSONObject subJsonObject=(JSONObject)value;
      Map<String,Object> subMap=transferMapByJson(subJsonObject);
      map.put(key,subMap);
    }
 else {
      map.put(key,value);
    }
  }
  return map;
}
