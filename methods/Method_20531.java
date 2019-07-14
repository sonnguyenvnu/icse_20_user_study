@Provides @Singleton @NonNull static GraphQLInterceptor provideGraphQLInterceptor(final @NonNull CurrentUserType currentUser){
  return new GraphQLInterceptor(currentUser);
}
