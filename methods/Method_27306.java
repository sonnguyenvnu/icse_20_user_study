public static ScrapService getWiki(){
  return new Retrofit.Builder().baseUrl("https://github.com/").client(provideOkHttpClient()).addConverterFactory(new GithubResponseConverter(new Gson())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(ScrapService.class);
}
