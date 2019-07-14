/** 
 * Returns all cookies from client that matches provided name.
 * @see #getCookie(javax.servlet.http.HttpServletRequest,String) 
 */
public static Cookie[] getAllCookies(final HttpServletRequest request,final String cookieName){
  Cookie[] cookies=request.getCookies();
  if (cookies == null) {
    return null;
  }
  ArrayList<Cookie> list=new ArrayList<>(cookies.length);
  for (  Cookie cookie : cookies) {
    if (cookie.getName().equals(cookieName)) {
      list.add(cookie);
    }
  }
  if (list.isEmpty()) {
    return null;
  }
  return list.toArray(new Cookie[0]);
}
