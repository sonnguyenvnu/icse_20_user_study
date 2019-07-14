/** 
 * ???????demo??????????
 * @param request
 * @return
 */
public String getFunctionDetail(@NotNull JSONObject request){
  return getFunctionCall(request.getString("name"),request.getString("arguments")) + ": " + StringUtil.getTrimedString(request.getString("detail"));
}
