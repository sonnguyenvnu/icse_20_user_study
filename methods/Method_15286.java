/** 
 * @param s
 * @return
 */
public static JSONObject parseObject(Object obj){
  return parseObject(toJSONString(obj));
}
