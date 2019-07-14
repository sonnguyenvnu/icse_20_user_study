/** 
 * Logouts the specified user.
 * @param userId   the specified user id
 * @param response the specified response
 */
public static void logout(final String userId,final HttpServletResponse response){
  if (null != response) {
    final Cookie cookie=new Cookie(COOKIE_NAME,null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
  }
  SESSION_CACHE.remove(userId);
  final BeanManager beanManager=BeanManager.getInstance();
  final UserMgmtService userMgmtService=beanManager.getReference(UserMgmtService.class);
  userMgmtService.updateOnlineStatus(userId,"",false,true);
}
