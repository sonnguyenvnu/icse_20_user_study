/** 
 * Get the http connection timeout for the connection. If the default value of zero is returned then the default rescu timeout will be applied. Check the exchange code to see if this option has been implemented.
 * @return the http read timeout in milliseconds
 */
public int getHttpConnTimeout(){
  return httpConnTimeout;
}
