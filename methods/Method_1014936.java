/** 
 * ??Cookie
 * @param request
 * @param response
 * @param key
 */
public static void remove(HttpServletRequest request,HttpServletResponse response,String key){
  Cookie cookie=get(request,key);
  if (cookie != null) {
    set(response,key,"",null,COOKIE_PATH,0,true);
  }
}
