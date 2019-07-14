/** 
 * Gets CSRF token from the specified request.
 * @param context the specified request context
 * @return CSRF token, returns {@code ""} if not found
 */
public static String getCSRFToken(final RequestContext context){
  final JSONObject user=Sessions.getUser();
  if (null == user) {
    return "";
  }
  final String userId=user.optString(Keys.OBJECT_ID);
  if (StringUtils.isBlank(userId)) {
    return "";
  }
  JSONObject csrfTokenValue=SESSION_CACHE.get(userId + Common.CSRF_TOKEN);
  if (null == csrfTokenValue) {
    csrfTokenValue=new JSONObject();
    csrfTokenValue.put(Common.DATA,RandomStringUtils.randomAlphanumeric(12));
    SESSION_CACHE.put(userId + Common.CSRF_TOKEN,csrfTokenValue);
  }
  return csrfTokenValue.optString(Common.DATA);
}
