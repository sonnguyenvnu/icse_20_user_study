/** 
 * Returns  {@code true} if status code indicates an error.
 */
public static boolean isError(final int statusCode){
  return statusCode >= 500;
}
