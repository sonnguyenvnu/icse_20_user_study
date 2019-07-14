/** 
 * Logins the specified user.
 * @param response      the specified response
 * @param userId        the specified user id, for example,
 * @param rememberLogin remember login or not
 * @return token, returns {@code null} if login failed
 */
public static String login(final HttpServletResponse response,final String userId,final boolean rememberLogin){
  try {
    final BeanManager beanManager=BeanManager.getInstance();
    final UserRepository userRepository=beanManager.getReference(UserRepository.class);
    final JSONObject user=userRepository.get(userId);
    if (null == user) {
      LOGGER.log(Level.WARN,"Login user [id=" + userId + "] failed");
      return null;
    }
    SESSION_CACHE.put(userId,user);
    final JSONObject csrfToken=new JSONObject();
    csrfToken.put(Common.DATA,RandomStringUtils.randomAlphanumeric(12));
    SESSION_CACHE.put(userId + Common.CSRF_TOKEN,csrfToken);
    final JSONObject cookieJSONObject=new JSONObject();
    cookieJSONObject.put(Keys.OBJECT_ID,user.optString(Keys.OBJECT_ID));
    final String random=RandomStringUtils.randomAlphanumeric(16);
    cookieJSONObject.put(Keys.TOKEN,user.optString(User.USER_PASSWORD) + COOKIE_ITEM_SEPARATOR + random);
    cookieJSONObject.put(Common.REMEMBER_LOGIN,rememberLogin);
    final String ret=Crypts.encryptByAES(cookieJSONObject.toString(),Symphonys.COOKIE_SECRET);
    final Cookie cookie=new Cookie(COOKIE_NAME,ret);
    cookie.setPath("/");
    cookie.setMaxAge(rememberLogin ? COOKIE_EXPIRY : -1);
    cookie.setHttpOnly(true);
    cookie.setSecure(StringUtils.equalsIgnoreCase(Latkes.getServerScheme(),"https"));
    response.addCookie(cookie);
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.WARN,"Login user [id=" + userId + "] failed",e);
    return null;
  }
}
