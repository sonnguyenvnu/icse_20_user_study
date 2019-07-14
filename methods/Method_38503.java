/** 
 * Builds a TRACE request.
 */
public static HttpRequest trace(final String destination){
  return new HttpRequest().method(HttpMethod.TRACE).set(destination);
}
