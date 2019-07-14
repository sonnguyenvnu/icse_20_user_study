@Override protected boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue) throws UnauthorizedException {
  HttpServletRequest httpServletRequest=(HttpServletRequest)request;
  SystemProperties properties=SpringContextUtil.getBean(SystemProperties.class);
  String[] anonUrl=StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(),",");
  boolean match=false;
  for (  String u : anonUrl) {
    if (pathMatcher.match(u,httpServletRequest.getRequestURI()))     match=true;
  }
  if (match)   return true;
  if (isLoginAttempt(request,response)) {
    return executeLogin(request,response);
  }
  return false;
}
