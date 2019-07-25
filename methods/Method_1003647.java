/** 
 * A handler that delegates to the next handler if the request is OPTIONS, otherwise raises a 405 client error.
 * @return A handler
 * @since 1.1
 */
public static Handler options(){
  return MethodHandler.OPTIONS;
}
