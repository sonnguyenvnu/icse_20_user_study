private static Retrofit provideRetrofit(boolean enterprise){
  return new Retrofit.Builder().baseUrl(enterprise && PrefGetter.isEnterprise() ? LinkParserHelper.getEndpoint(PrefGetter.getEnterpriseUrl()) : BuildConfig.REST_URL).client(provideOkHttpClient()).addConverterFactory(new GithubResponseConverter(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
}
