/** 
 * ??
 * @param request
 * @param array
 * @param position
 * @return
 */
@Override public Object getFromArray(@NotNull JSONObject request,String array,String position){
  return BaseModel.get(request.getJSONArray(array),request.getIntValue(position));
}
