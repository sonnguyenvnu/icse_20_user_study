/** 
 * JSONObject????
 * @param object
 * @param clazz
 * @return
 */
public static <T>T parseObject(JSONObject object,Class<T> clazz){
  return parseObject(toJSONString(object),clazz);
}
