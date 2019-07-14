/** 
 * TODO ????? "key-()":"verifyAccess()"
 * @param request
 * @return
 * @throws Exception
 */
public Object verifyAccess(@NotNull JSONObject request) throws Exception {
  long userId=request.getLongValue(zuo.biao.apijson.JSONObject.KEY_USER_ID);
  RequestRole role=RequestRole.get(request.getString(zuo.biao.apijson.JSONObject.KEY_ROLE));
  if (role == RequestRole.OWNER && userId != DemoVerifier.getVisitorId(session)) {
    throw new IllegalAccessException("???????OWNER????");
  }
  return null;
}
