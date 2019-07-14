/** 
 * Fired when the request progress, override to handle in your own code
 * @param responseBody response body received so far
 */
public void onProgressData(byte[] responseBody){
  AsyncHttpClient.log.d(LOG_TAG,"onProgressData(byte[]) was not overriden, but callback was received");
}
