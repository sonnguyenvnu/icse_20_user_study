/** 
 * Gets the current user with the specified request.
 * @param request the specified request
 * @return the current user, returns {@code null} if not logged in
 */
public static JSONObject currentUser(final HttpServletRequest request){
  final Cookie[] cookies=request.getCookies();
  if (null == cookies || 0 == cookies.length) {
    return null;
  }
  try {
    for (    final Cookie cookie : cookies) {
      if (!Sessions.COOKIE_NAME.equals(cookie.getName())) {
        continue;
      }
      final String value=Crypts.decryptByAES(cookie.getValue(),Symphonys.COOKIE_SECRET);
      final JSONObject cookieJSONObject=new JSONObject(value);
      final String userId=cookieJSONObject.optString(Keys.OBJECT_ID);
      if (StringUtils.isBlank(userId)) {
        return null;
      }
      JSONObject ret=SESSION_CACHE.get(userId);
      if (null == ret) {
        ret=tryLogInWithCookie(cookieJSONObject,request);
      }
      if (null == ret) {
        return null;
      }
      final String token=cookieJSONObject.optString(Keys.TOKEN);
      final String password=StringUtils.substringBeforeLast(token,COOKIE_ITEM_SEPARATOR);
      final String userPassword=ret.optString(User.USER_PASSWORD);
      if (!userPassword.equals(password)) {
        return null;
      }
      if (UserExt.USER_STATUS_C_INVALID == ret.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_INVALID_LOGIN == ret.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_DEACTIVATED == ret.optInt(UserExt.USER_STATUS)) {
        SESSION_CACHE.remove(userId);
        return null;
      }
      final String ip=Requests.getRemoteAddr(request);
      if (StringUtils.isNotBlank(ip)) {
        ret.put(UserExt.USER_LATEST_LOGIN_IP,ip);
        SESSION_CACHE.put(userId,ret);
      }
      return JSONs.clone(ret);
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.WARN,"Parses cookie failed, clears cookie");
  }
  return null;
}
