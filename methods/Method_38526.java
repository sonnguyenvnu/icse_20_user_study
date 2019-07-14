/** 
 * Returns  {@code true} if status code indicates a redirect.
 */
public static boolean isRedirect(final int statusCode){
  return statusCode >= 300 && statusCode < 400;
}
