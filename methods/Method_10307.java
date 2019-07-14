@Override public final void onFailure(final int statusCode,final Header[] headers,final byte[] responseBytes,final Throwable throwable){
  if (responseBytes != null) {
    Runnable parser=new Runnable(){
      @Override public void run(){
        try {
          final Object jsonResponse=parseResponse(responseBytes);
          postRunnable(new Runnable(){
            @Override public void run(){
              if (!useRFC5179CompatibilityMode && jsonResponse == null) {
                onFailure(statusCode,headers,(String)null,throwable);
              }
 else               if (jsonResponse instanceof JSONObject) {
                onFailure(statusCode,headers,throwable,(JSONObject)jsonResponse);
              }
 else               if (jsonResponse instanceof JSONArray) {
                onFailure(statusCode,headers,throwable,(JSONArray)jsonResponse);
              }
 else               if (jsonResponse instanceof String) {
                onFailure(statusCode,headers,(String)jsonResponse,throwable);
              }
 else {
                onFailure(statusCode,headers,new JSONException("Unexpected response type " + jsonResponse.getClass().getName()),(JSONObject)null);
              }
            }
          }
);
        }
 catch (        final JSONException ex) {
          postRunnable(new Runnable(){
            @Override public void run(){
              onFailure(statusCode,headers,ex,(JSONObject)null);
            }
          }
);
        }
      }
    }
;
    if (!getUseSynchronousMode() && !getUsePoolThread()) {
      new Thread(parser).start();
    }
 else {
      parser.run();
    }
  }
 else {
    AsyncHttpClient.log.v(LOG_TAG,"response body is null, calling onFailure(Throwable, JSONObject)");
    onFailure(statusCode,headers,throwable,(JSONObject)null);
  }
}
