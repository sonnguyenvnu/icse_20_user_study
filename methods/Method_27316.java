public static OkHttpClient provideOkHttpClient(){
  if (okHttpClient == null) {
    OkHttpClient.Builder client=new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    }
    client.addInterceptor(new AuthenticationInterceptor());
    client.addInterceptor(new PaginationInterceptor());
    client.addInterceptor(new ContentTypeInterceptor());
    okHttpClient=client.build();
  }
  return okHttpClient;
}
