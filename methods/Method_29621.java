@Override public void addLoginStatus(HttpServletRequest request,HttpServletResponse response,String userId){
  Cookie cookie=new Cookie(LOGIN_USER_STATUS_NAME,userId);
  cookie.setDomain(ConstUtils.COOKIE_DOMAIN);
  cookie.setPath("/");
  cookie.setMaxAge(-1);
  response.addCookie(cookie);
}
