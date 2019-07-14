@Override public final void onSuccess(final int statusCode,final Header[] headers,final String responseString){
  if (statusCode != HttpStatus.SC_NO_CONTENT) {
    Runnable parser=new Runnable(){
      @Override public void run(){
        try {
          final JSON_TYPE jsonResponse=parseResponse(responseString,false);
          postRunnable(new Runnable(){
            @Override public void run(){
              onSuccess(statusCode,headers,responseString,jsonResponse);
            }
          }
);
        }
 catch (        final Throwable t) {
          AsyncHttpClient.log.d(LOG_TAG,"parseResponse thrown an problem",t);
          postRunnable(new Runnable(){
            @Override public void run(){
              onFailure(statusCode,headers,t,responseString,null);
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
    onSuccess(statusCode,headers,null,null);
  }
}
