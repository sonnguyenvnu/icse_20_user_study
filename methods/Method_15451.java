/** 
 * obj?JSONObject
 * @param json
 * @return
 */
public static JSONObject parseObject(Object obj){
  if (obj instanceof JSONObject) {
    return (JSONObject)obj;
  }
  return parseObject(toJSONString(obj));
}
