private static @NonNull Retrofit createRetrofit(final @NonNull String baseUrl,final @NonNull Gson gson,final @NonNull OkHttpClient okHttpClient){
  return new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
}
