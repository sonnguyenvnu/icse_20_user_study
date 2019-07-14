/** 
 * If the incoming request contains user credentials in headers or parameters then extract them here into an Authentication token that can be validated later. This implementation only recognises password grant requests and extracts the username and password.
 * @param request the incoming request, possibly with user credentials
 * @return an authentication for validation (or null if there is no further authentication)
 */
protected Authentication extractCredentials(HttpServletRequest request){
  String grantType=request.getParameter("grant_type");
  if (grantType != null && grantType.equals("password")) {
    UsernamePasswordAuthenticationToken result=new UsernamePasswordAuthenticationToken(request.getParameter("username"),request.getParameter("password"));
    result.setDetails(authenticationDetailsSource.buildDetails(request));
    return result;
  }
  return null;
}
