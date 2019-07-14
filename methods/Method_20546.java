@Provides @Singleton @WebEndpoint @NonNull static String provideWebEndpoint(final @NonNull ApiEndpoint apiEndpoint){
  return (apiEndpoint == ApiEndpoint.PRODUCTION) ? "https://www.kickstarter.com" : apiEndpoint.url().replaceAll("(?<=\\Ahttps?:\\/\\/)api.","");
}
