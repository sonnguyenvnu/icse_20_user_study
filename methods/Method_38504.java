/** 
 * Builds an OPTIONS request.
 */
public static HttpRequest options(final String destination){
  return new HttpRequest().method(HttpMethod.OPTIONS).set(destination);
}
