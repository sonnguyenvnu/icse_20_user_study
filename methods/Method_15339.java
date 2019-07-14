/** 
 * ??
 * @param request
 * @param array
 * @param position ?????????? getFromArray(array,0) ?????????????? "@position": 0, "result()": "getFromArray(array,@position)"
 * @return
 */
public Object getFromArray(@NotNull JSONObject request,String array,String position){
  int p;
  try {
    p=Integer.parseInt(position);
  }
 catch (  Exception e) {
    p=request.getIntValue(position);
  }
  return BaseModel.get(request.getJSONArray(array),p);
}
