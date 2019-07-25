public static void remove(String username){
  cache.remove(username);
  Cookie cookie=new Cookie("auth",null);
  ResponseContext.getCurrent().addCookie(cookie);
  UserContext.setCurrent(null);
}
