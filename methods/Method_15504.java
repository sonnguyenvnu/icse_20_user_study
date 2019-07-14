/** 
 * ????json???????
 * @param request ?parseRequest?URLDecoder.decode(request, UTF_8);?parseResponse(getCorrectRequest(...))
 * @return parseResponse(requestObject);
 */
@NotNull @Override public JSONObject parseResponse(String request){
  Log.d(TAG,"\n\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n" + requestMethod + "/parseResponse  request = \n" + request + "\n\n");
  try {
    requestObject=parseRequest(request);
  }
 catch (  Exception e) {
    return newErrorResult(e);
  }
  return parseResponse(requestObject);
}
