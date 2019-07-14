/** 
 * Defines the socket timeout (SO_TIMEOUT) in milliseconds, which is the timeout for waiting for data or, put differently, a maximum period inactivity between two consecutive data packets). A timeout value of zero is interpreted as an infinite timeout.
 */
public HttpRequest connectionTimeout(final int milliseconds){
  this.connectTimeout=milliseconds;
  return this;
}
