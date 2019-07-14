@Provides @Singleton @ActivitySamplePreference @NonNull static IntPreferenceType provideActivitySamplePreference(final @NonNull SharedPreferences sharedPreferences){
  return new IntPreference(sharedPreferences,SharedPreferenceKey.LAST_SEEN_ACTIVITY_ID);
}
