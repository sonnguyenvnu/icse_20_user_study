/** 
 * Tries to authenticate user via token. Returns the token if user is authenticated. Returned token may be rotated.
 */
protected T authenticateUserViaToken(final ActionRequest actionRequest){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final String token=ServletUtil.resolveAuthBearerToken(servletRequest);
  if (token == null) {
    return null;
  }
  final T authToken=userAuth().validateToken(token);
  if (authToken == null) {
    return null;
  }
  final T newAuthToken=userAuth().rotateToken(authToken);
  actionRequest.getHttpServletResponse().setHeader("Authentication","Bearer: " + userAuth().tokenValue(newAuthToken));
  return newAuthToken;
}
