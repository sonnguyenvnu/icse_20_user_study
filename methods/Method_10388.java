@Override public RequestHandle executeSample(AsyncHttpClient client,String URL,Header[] headers,HttpEntity entity,ResponseHandlerInterface responseHandler){
  return getAsyncHttpClient().get(this,getDefaultURL(),getRequestParams(),getResponseHandler());
}
