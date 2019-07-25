/** 
 * Perform a GET request.
 * @param url the URL
 * @return a new request object
 */
public static HttpRequest get(URL url){
  return request("GET",url);
}
