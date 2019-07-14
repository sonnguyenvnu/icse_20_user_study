/** 
 * ?????
 * @param request
 * @param value
 * @return
 */
@Override public float floatValue(@NotNull JSONObject request,String value){
  return request.getFloatValue(value);
}
