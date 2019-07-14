private HttpClientContext convertHttpClientContext(Request request,Site site,Proxy proxy){
  HttpClientContext httpContext=new HttpClientContext();
  if (proxy != null && proxy.getUsername() != null) {
    AuthState authState=new AuthState();
    authState.update(new BasicScheme(ChallengeState.PROXY),new UsernamePasswordCredentials(proxy.getUsername(),proxy.getPassword()));
    httpContext.setAttribute(HttpClientContext.PROXY_AUTH_STATE,authState);
  }
  if (request.getCookies() != null && !request.getCookies().isEmpty()) {
    CookieStore cookieStore=new BasicCookieStore();
    for (    Map.Entry<String,String> cookieEntry : request.getCookies().entrySet()) {
      BasicClientCookie cookie1=new BasicClientCookie(cookieEntry.getKey(),cookieEntry.getValue());
      cookie1.setDomain(UrlUtils.removePort(UrlUtils.getDomain(request.getUrl())));
      cookieStore.addCookie(cookie1);
    }
    httpContext.setCookieStore(cookieStore);
  }
  return httpContext;
}
