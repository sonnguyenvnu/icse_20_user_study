/** 
 * ?????????
 * @param object
 * @return
 */
public static String format(Object object){
  return toJSONString(object,SerializerFeature.PrettyFormat);
}
