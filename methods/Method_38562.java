/** 
 * Tries to login user with form data. Returns session object, otherwise returns <code>null</code>.
 */
protected T loginViaRequestParams(final HttpServletRequest servletRequest){
  final String username=servletRequest.getParameter(PARAM_USERNAME).trim();
  if (StringUtil.isEmpty(username)) {
    return null;
  }
  final String password=servletRequest.getParameter(PARAM_PASSWORD).trim();
  return userAuth.login(username,password);
}
