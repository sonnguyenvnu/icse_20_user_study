/** 
 * Common logic for OAuth failed. (Note that the default logic doesn't pass the failure through so as to not mess with the current authentication.)
 * @param request  The request.
 * @param response The response.
 * @param failure  The failure.
 * @throws ServletException in the case of an underlying Servlet API exception
 * @throws IOException in the case of general IO exceptions
 */
protected void fail(HttpServletRequest request,HttpServletResponse response,OAuthRequestFailedException failure) throws IOException, ServletException {
  try {
    request.getSession().setAttribute(OAUTH_FAILURE_KEY,failure);
  }
 catch (  Exception e) {
  }
  if (LOG.isDebugEnabled()) {
    LOG.debug(failure);
  }
  if (getOAuthFailureHandler() != null) {
    getOAuthFailureHandler().handle(request,response,failure);
  }
 else {
    throw failure;
  }
}
