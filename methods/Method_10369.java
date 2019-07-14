@Override public RequestHandle executeSample(AsyncHttpClient client,String URL,Header[] headers,HttpEntity entity,ResponseHandlerInterface responseHandler){
  RequestParams params=new RequestParams();
  params.setUseJsonStreamer(true);
  JSONObject body;
  if (isRequestBodyAllowed() && (body=getBodyTextAsJSON()) != null) {
    try {
      Iterator keys=body.keys();
      Log.d(LOG_TAG,"JSON data:");
      while (keys.hasNext()) {
        String key=(String)keys.next();
        Log.d(LOG_TAG,"  " + key + ": " + body.get(key));
        params.put(key,body.get(key).toString());
      }
    }
 catch (    JSONException e) {
      Log.w(LOG_TAG,"Unable to retrieve a JSON value",e);
    }
  }
  return client.post(this,URL,headers,params,RequestParams.APPLICATION_JSON,responseHandler);
}
