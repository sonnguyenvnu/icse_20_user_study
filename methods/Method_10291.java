/** 
 * Fired when a retry occurs, override to handle in your own code
 * @param retryNo number of retry
 */
public void onRetry(int retryNo){
  AsyncHttpClient.log.d(LOG_TAG,String.format("Request retry no. %d",retryNo));
}
