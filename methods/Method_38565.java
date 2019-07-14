/** 
 * Tries to authenticate user via HTTP session. Returns the token if user is authenticated. Returned token may be rotated.
 */
protected T authenticateUserViaHttpSession(final ActionRequest actionRequest){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final UserSession<T> userSession=UserSession.get(servletRequest);
  if (userSession == null) {
    return null;
  }
  final T authToken=userSession.getAuthToken();
  if (authToken == null) {
    return null;
  }
  final T newAuthToken=userAuth().rotateToken(authToken);
  if (newAuthToken != authToken) {
    final UserSession<T> newUserSesion=new UserSession<>(newAuthToken,userAuth().tokenValue(newAuthToken));
    newUserSesion.start(servletRequest,actionRequest.getHttpServletResponse());
  }
  return newAuthToken;
}
