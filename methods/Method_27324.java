@NonNull public static Observable<GitHubStatusModel> gitHubStatus(){
  return new Retrofit.Builder().baseUrl("https://status.github.com/").client(provideOkHttpClient()).addConverterFactory(new GithubResponseConverter(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(ContentService.class).checkStatus();
}
