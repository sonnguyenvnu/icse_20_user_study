/** 
 * ??cookie
 * @param response
 * @param name
 * @param value
 * @param maxAge
 */
public static void setCookie(HttpServletResponse response,String name,String value,String path,int maxAge){
  Cookie cookie=new Cookie(name,value);
  cookie.setPath(path);
  if (maxAge > 0) {
    cookie.setMaxAge(maxAge);
  }
  response.addCookie(cookie);
}
