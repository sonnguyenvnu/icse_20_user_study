/** 
 * Set the state of HttpClient metrics. <p> Default is false. </p>
 * @param enabled True if HttpClient metrics are published. False otherwise
 * @return {@code this}
 */
public HttpClientConfig enable(boolean enabled){
  this.enabled=enabled;
  return this;
}
