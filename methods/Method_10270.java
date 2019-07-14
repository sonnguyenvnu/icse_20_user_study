/** 
 * Sets the maximum number of retries and timeout for a particular Request.
 * @param retries maximum number of retries per request
 * @param timeout sleep between retries in milliseconds
 */
public void setMaxRetriesAndTimeout(int retries,int timeout){
  this.httpClient.setHttpRequestRetryHandler(new RetryHandler(retries,timeout));
}
