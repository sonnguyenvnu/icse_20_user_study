@Provides @Singleton static String provideClientId(final @NonNull ApiEndpoint apiEndpoint){
  return apiEndpoint == ApiEndpoint.PRODUCTION ? Secrets.Api.Client.PRODUCTION : Secrets.Api.Client.STAGING;
}
