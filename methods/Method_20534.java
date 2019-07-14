@Provides @Singleton @AccessTokenPreference @NonNull static StringPreferenceType provideAccessTokenPreference(final @NonNull SharedPreferences sharedPreferences){
  return new StringPreference(sharedPreferences,SharedPreferenceKey.ACCESS_TOKEN);
}
