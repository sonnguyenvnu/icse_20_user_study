public static String getToken(HttpServletRequest request){
  Cookie cookie=CookiesUtilities.getCookie(request,COOKIE_NAME);
  return (cookie == null) ? null : cookie.getValue();
}
