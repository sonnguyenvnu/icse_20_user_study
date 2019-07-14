private static OkHttpClient provideOkHttpClient(@Nullable String authToken,@Nullable String otp){
  OkHttpClient.Builder client=new OkHttpClient.Builder();
  if (BuildConfig.DEBUG) {
    client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
  }
  client.addInterceptor(new AuthenticationInterceptor(authToken,otp));
  return client.build();
}
