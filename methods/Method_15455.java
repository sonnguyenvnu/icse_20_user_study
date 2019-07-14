/** 
 * obj?JSONArray
 * @param obj
 * @return
 */
public static JSONArray parseArray(Object obj){
  if (obj instanceof JSONArray) {
    return (JSONArray)obj;
  }
  return parseArray(toJSONString(obj));
}
