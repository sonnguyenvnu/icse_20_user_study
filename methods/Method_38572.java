/** 
 * Starts new user session.
 */
public void start(final HttpServletRequest httpServletRequest,final HttpServletResponse httpServletResponse){
  final HttpSession httpSession=httpServletRequest.getSession(true);
  httpSession.setAttribute(AUTH_SESSION_NAME,this);
  final Cookie cookie=new Cookie(AUTH_COOKIE_NAME,authTokenValue);
  cookie.setMaxAge(cookieMaxAge);
  cookie.setPath("/");
  httpServletResponse.addCookie(cookie);
}
