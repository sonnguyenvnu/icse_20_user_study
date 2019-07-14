private static OkHttpClient.Builder createHttpClientBuilder(Interceptor... networkInterceptors){
  OkHttpClient.Builder builder=new OkHttpClient.Builder();
  for (  Interceptor interceptor : networkInterceptors) {
    builder.addNetworkInterceptor(interceptor);
  }
  return builder.addNetworkInterceptor(new StethoInterceptor());
}
