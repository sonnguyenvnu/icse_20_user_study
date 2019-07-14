/** 
 * Tires to authenticate user via the basic authentication. Returns the token if user is authenticated.
 */
protected T authenticateUserViaBasicAuth(final ActionRequest actionRequest){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final String username=ServletUtil.resolveAuthUsername(servletRequest);
  if (username == null) {
    return null;
  }
  final String password=ServletUtil.resolveAuthPassword(servletRequest);
  final T authToken=userAuth().login(username,password);
  if (authToken == null) {
    return null;
  }
  return authToken;
}
