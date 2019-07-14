/** 
 * Returns when request succeeds
 * @param statusCode http response status line
 * @param headers    response headers if any
 * @param response   parsed response if any
 */
public void onSuccess(int statusCode,Header[] headers,JSONArray response){
  AsyncHttpClient.log.w(LOG_TAG,"onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
}
