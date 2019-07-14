/** 
 * Creates an operation with the specified request and code,
 * @param request the specified request
 * @param code    the specified code
 * @param dataId  the specified data id
 * @return an operation
 */
public static JSONObject newOperation(final HttpServletRequest request,final int code,final String dataId){
  final String ip=Requests.getRemoteAddr(request);
  final String ua=Headers.getHeader(request,Common.USER_AGENT,"");
  final JSONObject user=Sessions.getUser();
  return new JSONObject().put(Operation.OPERATION_USER_ID,user.optString(Keys.OBJECT_ID)).put(Operation.OPERATION_CREATED,System.currentTimeMillis()).put(Operation.OPERATION_CODE,code).put(Operation.OPERATION_DATA_ID,dataId).put(Operation.OPERATION_IP,ip).put(Operation.OPERATION_UA,ua);
}
