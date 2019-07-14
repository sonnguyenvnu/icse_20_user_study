/** 
 * json?JSONObject
 * @param json
 * @return
 */
public static JSONObject parseObject(String json){
  int features=com.alibaba.fastjson.JSON.DEFAULT_PARSER_FEATURE;
  features|=Feature.OrderedField.getMask();
  return parseObject(json,features);
}
