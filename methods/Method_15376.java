/** 
 * @param requestObject
 * @param key
 * @param msg ????
 * @return
 */
public static JSONObject newIllegalArgumentResult(JSONObject requestObject,String key,String msg){
  return DemoParser.extendErrorResult(requestObject,new IllegalArgumentException(key + ":value ?value????" + StringUtil.getString(msg)));
}
