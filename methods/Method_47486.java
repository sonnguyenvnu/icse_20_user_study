@Override public void decide(Authentication authentication,Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
  HttpServletRequest request=((FilterInvocation)object).getHttpRequest();
  String url, method;
  AntPathRequestMatcher matcher;
  for (  GrantedAuthority ga : authentication.getAuthorities()) {
    if (ga instanceof MyGrantedAuthority) {
      MyGrantedAuthority urlGrantedAuthority=(MyGrantedAuthority)ga;
      url=urlGrantedAuthority.getPermissionUrl();
      method=urlGrantedAuthority.getMethod();
      matcher=new AntPathRequestMatcher(url);
      if (matcher.matches(request)) {
        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
          return;
        }
      }
    }
 else     if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
      matcher=new AntPathRequestMatcher("/login");
      if (matcher.matches(request)) {
        return;
      }
    }
  }
  throw new AccessDeniedException("no right");
}
