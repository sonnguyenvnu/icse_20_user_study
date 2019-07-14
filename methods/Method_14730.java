/** 
 * @param json
 * @return
 */
public static Object parse(Object obj){
  int features=com.alibaba.fastjson.JSON.DEFAULT_PARSER_FEATURE;
  features|=Feature.OrderedField.getMask();
  try {
    return com.alibaba.fastjson.JSON.parse(obj instanceof String ? (String)obj : toJSONString(obj),features);
  }
 catch (  Exception e) {
    Log.i(TAG,"parse  catch \n" + e.getMessage());
  }
  return null;
}
