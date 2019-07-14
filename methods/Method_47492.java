@Override public void decide(Authentication authentication,Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
  HttpServletRequest request=((FilterInvocation)object).getHttpRequest();
  String url, method;
  if ("anonymousUser".equals(authentication.getPrincipal()) || matchers("/images/**",request) || matchers("/js/**",request) || matchers("/css/**",request) || matchers("/fonts/**",request) || matchers("/",request) || matchers("/index.html",request) || matchers("/favicon.ico",request) || matchers("/login",request)) {
    return;
  }
 else {
    for (    GrantedAuthority ga : authentication.getAuthorities()) {
      if (ga instanceof UrlGrantedAuthority) {
        UrlGrantedAuthority urlGrantedAuthority=(UrlGrantedAuthority)ga;
        url=urlGrantedAuthority.getPermissionUrl();
        method=urlGrantedAuthority.getMethod();
        if (matchers(url,request)) {
          if (method.equals(request.getMethod()) || "ALL".equals(method)) {
            return;
          }
        }
      }
    }
  }
  throw new AccessDeniedException("no right");
}
