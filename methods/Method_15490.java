/** 
 * ??????
 * @param key
 * @param value
 * @return whether parse succeed
 */
@Override public boolean onParse(@NotNull String key,@NotNull Object value) throws Exception {
  if (key.endsWith("@")) {
    if (value instanceof JSONObject) {
      String replaceKey=key.substring(0,key.length() - 1);
      JSONObject subquery=(JSONObject)value;
      String range=subquery.getString(JSONRequest.KEY_SUBQUERY_RANGE);
      if (range != null && JSONRequest.SUBQUERY_RANGE_ALL.equals(range) == false && JSONRequest.SUBQUERY_RANGE_ANY.equals(range) == false) {
        throw new IllegalArgumentException("??? " + path + "/" + key + ":{ range:value } ? value ??? [" + JSONRequest.SUBQUERY_RANGE_ALL + ", " + JSONRequest.SUBQUERY_RANGE_ANY + "] ?????");
      }
      JSONArray arr=parser.onArrayParse(subquery,AbstractParser.getAbsPath(path,replaceKey),"[]",true);
      JSONObject obj=arr == null || arr.isEmpty() ? null : arr.getJSONObject(0);
      if (obj == null) {
        throw new Exception("????????????? " + path + "/" + key + ":{ } ? Subquery ?????");
      }
      String from=subquery.getString(JSONRequest.KEY_SUBQUERY_FROM);
      JSONObject arrObj=from == null ? null : obj.getJSONObject(from);
      if (arrObj == null) {
        throw new IllegalArgumentException("??? " + path + "/" + key + ":{ from:value } ? value ??????? " + from + ":{} ????");
      }
      SQLConfig cfg=(SQLConfig)arrObj.get(AbstractParser.KEY_CONFIG);
      if (cfg == null) {
        throw new NotExistException(TAG + ".onParse  cfg == null");
      }
      Subquery s=new Subquery();
      s.setPath(path);
      s.setOriginKey(key);
      s.setOriginValue(subquery);
      s.setFrom(from);
      s.setRange(range);
      s.setKey(replaceKey);
      s.setConfig(cfg);
      key=replaceKey;
      value=s;
      parser.putQueryResult(AbstractParser.getAbsPath(path,key),s);
    }
 else     if (value instanceof String) {
      String replaceKey=key.substring(0,key.length() - 1);
      String targetPath=AbstractParser.getValuePath(type == TYPE_ITEM ? path : parentPath,new String((String)value));
      Object target=onReferenceParse(targetPath);
      Log.i(TAG,"onParse targetPath = " + targetPath + "; target = " + target);
      if (target == null) {
        Log.d(TAG,"onParse  target == null  >>  continue;");
        return true;
      }
      if (target instanceof Map) {
        Log.d(TAG,"onParse  target instanceof Map  >>  continue;");
        return false;
      }
      if (targetPath.equals(target)) {
        Log.d(TAG,"onParse  targetPath.equals(target)  >>");
        if (isTable && (key.startsWith("@") == false || JSONRequest.TABLE_KEY_LIST.contains(key))) {
          Log.e(TAG,"onParse  isTable && (key.startsWith(@) == false" + " || JSONRequest.TABLE_KEY_LIST.contains(key)) >>  return null;");
          return false;
        }
 else {
          Log.d(TAG,"onParse  isTable(table) == false >> continue;");
          return true;
        }
      }
      Log.i(TAG,"onParse    >>  key = replaceKey; value = target;");
      key=replaceKey;
      value=target;
      Log.d(TAG,"onParse key = " + key + "; value = " + value);
    }
 else {
      throw new IllegalArgumentException(path + "/" + key + ":value ? value ??? ????String ? SQL???JSONObject ?");
    }
  }
  if (key.endsWith("()")) {
    if (value instanceof String == false) {
      throw new IllegalArgumentException(path + "/" + key + ":value ? value ?????String?");
    }
    String k=key.substring(0,key.length() - 2);
    String type;
    if (k.endsWith("-")) {
      type="-";
      k=k.substring(0,k.length() - 1);
      parseFunction(request,k,(String)value);
    }
 else {
      if (k.endsWith("+")) {
        type="+";
        k=k.substring(0,k.length() - 1);
      }
 else {
        type="0";
      }
      Map<String,String> map=functionMap.get(type);
      if (map == null) {
        map=new LinkedHashMap<>();
      }
      map.put(k,(String)value);
      functionMap.put(type,map);
    }
  }
 else   if (isTable && key.startsWith("@") && JSONRequest.TABLE_KEY_LIST.contains(key) == false) {
    customMap.put(key,value);
  }
 else {
    sqlRequest.put(key,value);
  }
  return true;
}
