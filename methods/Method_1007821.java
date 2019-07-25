/** 
 * Perform a POST request.
 * @param url the URL
 * @return a new request object
 */
public static HttpRequest post(URL url){
  return request("POST",url);
}
