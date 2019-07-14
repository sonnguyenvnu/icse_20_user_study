@Provides @Singleton @GamesNewsletterPreference @NonNull static BooleanPreferenceType provideGamesNewsletterPreference(final @NonNull SharedPreferences sharedPreferences){
  return new BooleanPreference(sharedPreferences,SharedPreferenceKey.HAS_SEEN_GAMES_NEWSLETTER);
}
