@Provides @Singleton @UserPreference @NonNull static StringPreferenceType provideUserPreference(final @NonNull SharedPreferences sharedPreferences){
  return new StringPreference(sharedPreferences,SharedPreferenceKey.USER);
}
