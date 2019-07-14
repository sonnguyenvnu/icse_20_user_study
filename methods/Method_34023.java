/** 
 * Common logic for OAuth failed.
 * @param request  The request.
 * @param response The response.
 * @param failure  The failure.
 * @throws IOException thrown when there's an underlying IO exception
 * @throws ServletException thrown in the case of an underlying Servlet exception 
 */
protected void fail(HttpServletRequest request,HttpServletResponse response,AuthenticationException failure) throws IOException, ServletException {
  SecurityContextHolder.getContext().setAuthentication(null);
  if (log.isDebugEnabled()) {
    log.debug(failure);
  }
  authenticationEntryPoint.commence(request,response,failure);
}
