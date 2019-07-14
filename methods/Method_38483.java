/** 
 * Adds default header to all requests.
 */
public HttpBrowser setDefaultHeader(final String name,final String value){
  defaultHeaders.addHeader(name,value);
  return this;
}
