/** 
 * @param object
 * @param key
 * @param clazz
 * @return
 */
public static <T>List<T> getList(JSONObject object,String key,Class<T> clazz){
  return object == null ? null : JSON.parseArray(object.getString(formatArrayKey(key)),clazz);
}
