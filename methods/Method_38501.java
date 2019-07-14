/** 
 * Builds a DELETE request.
 */
public static HttpRequest delete(final String destination){
  return new HttpRequest().method(HttpMethod.DELETE).set(destination);
}
