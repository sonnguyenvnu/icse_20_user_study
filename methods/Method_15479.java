/** 
 * ???key??
 * @param object
 * @return
 */
public static JSONObject format(final JSONObject object){
  if (object == null || object.isEmpty()) {
    Log.i(TAG,"format  object == null || object.isEmpty() >> return object;");
    return object;
  }
  JSONObject formatedObject=new JSONObject(true);
  Set<String> set=object.keySet();
  if (set != null) {
    Object value;
    for (    String key : set) {
      value=object.get(key);
      if (value instanceof JSONArray) {
        formatedObject.put(formatArrayKey(key),format((JSONArray)value));
      }
 else       if (value instanceof JSONObject) {
        formatedObject.put(formatObjectKey(key),format((JSONObject)value));
      }
 else {
        formatedObject.put(formatOtherKey(key),value);
      }
    }
  }
  return formatedObject;
}
