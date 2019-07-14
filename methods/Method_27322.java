@NonNull public static UserRestService getContribution(){
  return new Retrofit.Builder().baseUrl(BuildConfig.REST_URL).addConverterFactory(new GithubResponseConverter(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(UserRestService.class);
}
