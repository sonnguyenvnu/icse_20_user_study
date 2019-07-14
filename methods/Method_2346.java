/** 
 * ??cookie
 * @param response
 * @param name
 * @return
 */
public static void removeCookie(HttpServletResponse response,String name){
  setCookie(response,name,"","/",0);
}
