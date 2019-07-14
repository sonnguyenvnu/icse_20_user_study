/** 
 * Builds a GET request.
 */
public static HttpRequest get(final String destination){
  return new HttpRequest().method(HttpMethod.GET).set(destination);
}
