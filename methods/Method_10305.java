@Override public void onFailure(int statusCode,Header[] headers,String responseString,Throwable throwable){
  AsyncHttpClient.log.w(LOG_TAG,"onFailure(int, Header[], String, Throwable) was not overriden, but callback was received",throwable);
}
