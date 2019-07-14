/** 
 * Generic request builder, usually used when method is a variable. Otherwise, use one of the other static request builder methods.
 */
public static HttpRequest create(final String method,final String destination){
  return new HttpRequest().method(method.toUpperCase()).set(destination);
}
