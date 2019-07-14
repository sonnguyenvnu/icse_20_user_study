/** 
 * Builds a POST request.
 */
public static HttpRequest post(final String destination){
  return new HttpRequest().method(HttpMethod.POST).set(destination);
}
