/** 
 * A handler that delegates to the next handler if the request is PUT, otherwise raises a 405 client error.
 * @return A handler
 */
public static Handler put(){
  return MethodHandler.PUT;
}
