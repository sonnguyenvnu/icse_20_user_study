/** 
 * Builds a PATCH request.
 */
public static HttpRequest patch(final String destination){
  return new HttpRequest().method(HttpMethod.PATCH).set(destination);
}
