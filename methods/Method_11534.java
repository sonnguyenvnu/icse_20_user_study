private void generateCookie(HttpClientBuilder httpClientBuilder,Site site){
  if (site.isDisableCookieManagement()) {
    httpClientBuilder.disableCookieManagement();
    return;
  }
  CookieStore cookieStore=new BasicCookieStore();
  for (  Map.Entry<String,String> cookieEntry : site.getCookies().entrySet()) {
    BasicClientCookie cookie=new BasicClientCookie(cookieEntry.getKey(),cookieEntry.getValue());
    cookie.setDomain(site.getDomain());
    cookieStore.addCookie(cookie);
  }
  for (  Map.Entry<String,Map<String,String>> domainEntry : site.getAllCookies().entrySet()) {
    for (    Map.Entry<String,String> cookieEntry : domainEntry.getValue().entrySet()) {
      BasicClientCookie cookie=new BasicClientCookie(cookieEntry.getKey(),cookieEntry.getValue());
      cookie.setDomain(domainEntry.getKey());
      cookieStore.addCookie(cookie);
    }
  }
  httpClientBuilder.setDefaultCookieStore(cookieStore);
}
