public static Credentials getCredentials(HttpServletRequest request,Provider provider,Type type){
  Cookie cookie=CookiesUtilities.getCookie(request,type.getCookieName(provider));
  return (cookie == null) ? null : makeCredentials(cookie.getValue(),provider);
}
