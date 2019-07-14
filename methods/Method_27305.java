private static OkHttpClient provideOkHttpClient(){
  if (okHttpClient == null) {
    OkHttpClient.Builder client=new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    }
    client.addInterceptor(new AuthenticationInterceptor(true));
    okHttpClient=client.build();
  }
  return okHttpClient;
}
