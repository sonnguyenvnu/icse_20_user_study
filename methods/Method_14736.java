/** 
 * JSONArray??????
 * @param array
 * @param clazz
 * @return
 */
public static <T>List<T> parseArray(JSONArray array,Class<T> clazz){
  return parseArray(toJSONString(array),clazz);
}
