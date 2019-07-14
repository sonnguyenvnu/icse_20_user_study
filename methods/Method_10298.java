@Override public final void onFailure(final int statusCode,final Header[] headers,final String responseString,final Throwable throwable){
  if (responseString != null) {
    Runnable parser=new Runnable(){
      @Override public void run(){
        try {
          final JSON_TYPE jsonResponse=parseResponse(responseString,true);
          postRunnable(new Runnable(){
            @Override public void run(){
              onFailure(statusCode,headers,throwable,responseString,jsonResponse);
            }
          }
);
        }
 catch (        Throwable t) {
          AsyncHttpClient.log.d(LOG_TAG,"parseResponse thrown an problem",t);
          postRunnable(new Runnable(){
            @Override public void run(){
              onFailure(statusCode,headers,throwable,responseString,null);
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
    onFailure(statusCode,headers,throwable,null,null);
  }
}
