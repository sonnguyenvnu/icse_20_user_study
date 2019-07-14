@Provides @Singleton static CurrentUserType provideCurrentUser(final @AccessTokenPreference @NonNull StringPreferenceType accessTokenPreference,final @NonNull DeviceRegistrarType deviceRegistrar,final @NonNull Gson gson,final @NonNull @UserPreference StringPreferenceType userPreference){
  return new CurrentUser(accessTokenPreference,deviceRegistrar,gson,userPreference);
}
