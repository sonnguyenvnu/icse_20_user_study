protected void fetchWithRequest(final OkHttpNetworkFetchState fetchState,final NetworkFetcher.Callback callback,final Request request){
  final Call call=mOkHttpClient.newCall(request);
  fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks(){
    @Override public void onCancellationRequested(){
      if (Looper.myLooper() != Looper.getMainLooper()) {
        call.cancel();
      }
 else {
        mCancellationExecutor.execute(new Runnable(){
          @Override public void run(){
            call.cancel();
          }
        }
);
      }
    }
  }
);
  call.enqueue(new com.squareup.okhttp.Callback(){
    @Override public void onResponse(    Response response){
      fetchState.responseTime=SystemClock.uptimeMillis();
      final ResponseBody body=response.body();
      try {
        if (!response.isSuccessful()) {
          handleException(call,new IOException("Unexpected HTTP code " + response),callback);
          return;
        }
        long contentLength=body.contentLength();
        if (contentLength < 0) {
          contentLength=0;
        }
        callback.onResponse(body.byteStream(),(int)contentLength);
      }
 catch (      Exception e) {
        handleException(call,e,callback);
      }
 finally {
        try {
          body.close();
        }
 catch (        Exception e) {
          FLog.w(TAG,"Exception when closing response body",e);
        }
      }
    }
    @Override public void onFailure(    final Request request,    final IOException e){
      handleException(call,e,callback);
    }
  }
);
}
