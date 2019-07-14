@Provides @Singleton @NativeCheckoutPreference @NonNull static BooleanPreferenceType provideNativeCheckoutPreference(final @NonNull SharedPreferences sharedPreferences){
  return new BooleanPreference(sharedPreferences,SharedPreferenceKey.NATIVE_CHECKOUT);
}
