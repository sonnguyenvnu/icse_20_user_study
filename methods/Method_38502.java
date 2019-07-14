/** 
 * Builds a HEAD request.
 */
public static HttpRequest head(final String destination){
  return new HttpRequest().method(HttpMethod.HEAD).set(destination);
}
