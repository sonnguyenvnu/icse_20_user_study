/** 
 * client logout
 * @param request
 * @param response
 */
public static void logout(HttpServletRequest request,HttpServletResponse response){
  String cookieSessionId=CookieUtil.getValue(request,Conf.SSO_SESSIONID);
  if (cookieSessionId == null) {
    return;
  }
  String storeKey=SsoSessionIdHelper.parseStoreKey(cookieSessionId);
  if (storeKey != null) {
    SsoLoginStore.remove(storeKey);
  }
  CookieUtil.remove(request,response,Conf.SSO_SESSIONID);
}
