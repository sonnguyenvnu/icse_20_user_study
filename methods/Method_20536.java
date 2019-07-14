@Provides @Singleton @ConfigPreference @NonNull static StringPreferenceType providesConfigPreference(final @NonNull SharedPreferences sharedPreferences){
  return new StringPreference(sharedPreferences,SharedPreferenceKey.CONFIG);
}
