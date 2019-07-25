@Override public void reset(){
  OkHttpClient oldClient=client;
  client=new OkHttpClient.Builder().dns(new PinnedDns(ips)).connectTimeout(3,TimeUnit.SECONDS).addNetworkInterceptor(new IpTagInterceptor()).build();
  if (oldClient != null) {
    for (    Call call : oldClient.dispatcher().queuedCalls()) {
      call.cancel();
    }
    for (    Call call : oldClient.dispatcher().runningCalls()) {
      call.cancel();
    }
  }
}
