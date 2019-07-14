@Override public RequestHandle executeSample(AsyncHttpClient client,String URL,Header[] headers,HttpEntity entity,ResponseHandlerInterface responseHandler){
  if (fileSize > 0) {
    return client.get(this,URL,headers,null,responseHandler);
  }
 else {
    return client.head(this,URL,headers,null,responseHandler);
  }
}
