/** 
 * Create a new HTTP URL builder. <p> The protocol is set to HTTP, host to  {@code localhost} and port to the default for the protocol.
 * @return a new url builder
 */
static HttpUrlBuilder http(){
  return new DefaultHttpUrlBuilder();
}
