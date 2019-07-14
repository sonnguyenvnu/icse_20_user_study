/** 
 * Set the http connection timeout for the connection. If not supplied the default rescu timeout will be used. Check the exchange code to see if this option has been implemented. (This value can also be set globally in "rescu.properties" by setting the property "rescu.http.connTimeoutMillis".)
 * @param milliseconds the http read timeout in milliseconds
 */
public void setHttpConnTimeout(int milliseconds){
  this.httpConnTimeout=milliseconds;
}
