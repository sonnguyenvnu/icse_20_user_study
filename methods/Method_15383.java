/** 
 * ??
 * @param request
 * @param object
 * @param key
 * @return
 */
@Override public Object getFromObject(@NotNull JSONObject request,String object,String key){
  return BaseModel.get(request.getJSONObject(object),request.getString(key));
}
