/** 
 * Builds a PUT request.
 */
public static HttpRequest put(final String destination){
  return new HttpRequest().method(HttpMethod.PUT).set(destination);
}
