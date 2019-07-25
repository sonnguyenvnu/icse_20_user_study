@PostConstruct public void init(){
  CookieSerializer cookieSerializer=(this.cookieSerializer != null) ? this.cookieSerializer : createDefaultCookieSerializer();
  this.defaultHttpSessionIdResolver.setCookieSerializer(cookieSerializer);
}
