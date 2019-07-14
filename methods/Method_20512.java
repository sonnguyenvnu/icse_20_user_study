@Provides @Singleton @ApiEndpointPreference @NonNull StringPreferenceType provideApiEndpointPreference(final @NonNull SharedPreferences sharedPreferences){
  return new StringPreference(sharedPreferences,"debug_api_endpoint",ApiEndpoint.PRODUCTION.url());
}
