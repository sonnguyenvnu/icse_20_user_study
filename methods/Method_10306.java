@Override public void onSuccess(int statusCode,Header[] headers,String responseString){
  AsyncHttpClient.log.w(LOG_TAG,"onSuccess(int, Header[], String) was not overriden, but callback was received");
}
