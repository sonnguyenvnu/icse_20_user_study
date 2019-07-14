/** 
 * ??value??value?null???defaultValue
 * @param request
 * @param value
 * @param defaultValue
 * @return v == null ? request.get(defaultValue) : v
 */
public Object getWithDefault(@NotNull JSONObject request,String value,String defaultValue){
  Object v=request.get(value);
  return v == null ? request.get(defaultValue) : v;
}
