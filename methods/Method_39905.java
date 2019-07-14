/** 
 * Prevents HTTP cache.
 */
public static void preventCaching(final HttpServletResponse response){
  response.setHeader("Cache-Control","max-age=0, must-revalidate, no-cache, no-store, private, post-check=0, pre-check=0");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader("Expires",0);
}
