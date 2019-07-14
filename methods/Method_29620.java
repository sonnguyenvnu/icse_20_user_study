@Override public long getUserIdFromLoginStatus(HttpServletRequest request){
  Cookie[] cookies=request.getCookies();
  String cookiesId=null;
  if (cookies != null) {
    for (    Cookie cookie : cookies) {
      if (LOGIN_USER_STATUS_NAME.equals(cookie.getName())) {
        cookiesId=cookie.getValue();
      }
    }
  }
  return NumberUtils.toLong(cookiesId,-1);
}
