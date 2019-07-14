/** 
 * Returns when request failed
 * @param statusCode    http response status line
 * @param headers       response headers if any
 * @param throwable     throwable describing the way request failed
 * @param errorResponse parsed response if any
 */
public void onFailure(int statusCode,Header[] headers,Throwable throwable,JSONArray errorResponse){
  AsyncHttpClient.log.w(LOG_TAG,"onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received",throwable);
}
