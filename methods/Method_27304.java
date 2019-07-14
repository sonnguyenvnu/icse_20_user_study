private static OkHttpClient provideOkHttpClient(){
  OkHttpClient.Builder client=new OkHttpClient.Builder();
  if (BuildConfig.DEBUG) {
    client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
  }
  client.addInterceptor(chain -> {
    Request original=chain.request();
    Request.Builder requestBuilder=original.newBuilder();
    requestBuilder.header("Authorization","Client-ID " + BuildConfig.IMGUR_CLIENT_ID);
    requestBuilder.method(original.method(),original.body());
    Request request=requestBuilder.build();
    return chain.proceed(request);
  }
);
  return client.build();
}
