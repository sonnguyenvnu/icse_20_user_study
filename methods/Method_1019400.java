@Override public void reset(){
  OkHttpClient oldClient=client;
  OkHttpClient.Builder builder=new OkHttpClient.Builder().dns(db).connectTimeout(3,TimeUnit.SECONDS).addNetworkInterceptor(new IpTagInterceptor());
  if (interceptor != null) {
    builder.addNetworkInterceptor(interceptor);
  }
  client=builder.build();
  if (oldClient != null) {
    for (    Call call : oldClient.dispatcher().queuedCalls()) {
      call.cancel();
    }
    for (    Call call : oldClient.dispatcher().runningCalls()) {
      call.cancel();
    }
  }
}
