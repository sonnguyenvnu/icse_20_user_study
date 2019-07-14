/** 
 * ?????????GET???????????
 * @param method
 * @param request
 * @return
 * @throws Exception 
 */
@Override public JSONObject parseCorrectRequest() throws Exception {
  if (RequestMethod.isPublicMethod(requestMethod)) {
    return requestObject;
  }
  String tag=requestObject.getString(JSONRequest.KEY_TAG);
  if (StringUtil.isNotEmpty(tag,true) == false) {
    throw new IllegalArgumentException("???????tag????Table???? \"tag\": \"User\" ");
  }
  int version=requestObject.getIntValue(JSONRequest.KEY_VERSION);
  JSONObject object=null;
  String error="";
  try {
    object=getStructure("Request",JSONRequest.KEY_TAG,tag,version);
  }
 catch (  Exception e) {
    error=e.getMessage();
  }
  if (object == null) {
    throw new UnsupportedOperationException("????????Request????????????\n " + error);
  }
  JSONObject target=null;
  if (zuo.biao.apijson.JSONObject.isTableKey(tag) && object.containsKey(tag) == false) {
    target=new JSONObject(true);
    target.put(tag,object);
  }
 else {
    target=object;
  }
  requestObject.remove(JSONRequest.KEY_TAG);
  requestObject.remove(JSONRequest.KEY_VERSION);
  return parseCorrectRequest((JSONObject)target.clone());
}
