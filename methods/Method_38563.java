/** 
 * Tries to login user with basic authentication.
 */
protected T loginViaBasicAuth(final HttpServletRequest servletRequest){
  final String username=ServletUtil.resolveAuthUsername(servletRequest);
  if (username == null) {
    return null;
  }
  final String password=ServletUtil.resolveAuthPassword(servletRequest);
  return userAuth.login(username,password);
}
