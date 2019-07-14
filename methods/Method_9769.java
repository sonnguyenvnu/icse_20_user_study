private static void addCookie(final HttpServletResponse response,final String name,final String value){
  final Cookie cookie=new Cookie(name,value);
  cookie.setPath("/");
  cookie.setMaxAge(60 * 60 * 24);
  cookie.setHttpOnly(true);
  cookie.setSecure(StringUtils.equalsIgnoreCase(Latkes.getServerScheme(),"https"));
  response.addCookie(cookie);
}
