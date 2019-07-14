public Retrofit.Builder getBuilder(String apiUrl){
  Retrofit.Builder builder=new Retrofit.Builder();
  builder.client(getOkClient());
  builder.baseUrl(apiUrl);
  builder.addConverterFactory(new NullOnEmptyConverterFactory());
  builder.addConverterFactory(GsonConverterFactory.create(getGson()));
  builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
  return builder;
}
