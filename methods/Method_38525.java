/** 
 * Returns  {@code true} if status code indicates successful result.
 */
public static boolean isSuccessful(final int statusCode){
  return statusCode < 400;
}
