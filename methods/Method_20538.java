@Provides @Singleton @AppRatingPreference @NonNull static BooleanPreferenceType provideAppRatingPreference(final @NonNull SharedPreferences sharedPreferences){
  return new BooleanPreference(sharedPreferences,SharedPreferenceKey.HAS_SEEN_APP_RATING);
}
