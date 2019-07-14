/** 
 * ???json???id??
 * @param table
 * @param key
 * @param obj
 * @param targetKey 
 * @return null ? ?? : ?????
 */
private JSONObject getJoinObject(String table,JSONObject obj,String key){
  if (obj == null || obj.isEmpty()) {
    Log.e(TAG,"getIdList  obj == null || obj.isEmpty() >> return null;");
    return null;
  }
  if (StringUtil.isEmpty(key,true)) {
    Log.e(TAG,"getIdList  StringUtil.isEmpty(key, true) >> return null;");
    return null;
  }
  JSONObject requestObj=new JSONObject(true);
  Set<String> set=new LinkedHashSet<>(obj.keySet());
  for (  String k : set) {
    if (StringUtil.isEmpty(k,true)) {
      continue;
    }
    if (k.startsWith("@")) {
      if (JOIN_COPY_KEY_LIST.contains(k)) {
        requestObj.put(k,obj.get(k));
      }
    }
 else {
      if (k.endsWith("@")) {
        if (k.equals(key)) {
          continue;
        }
        throw new UnsupportedOperationException(table + "." + k + " ????" + JSONRequest.KEY_JOIN + " ???Table????1? key@:value ?");
      }
      if (k.contains("()") == false) {
        requestObj.put(k,obj.get(k));
      }
    }
  }
  return requestObj;
}
