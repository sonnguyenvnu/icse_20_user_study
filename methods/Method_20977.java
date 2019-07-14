@Override public @NonNull Observable<AccessTokenEnvelope> signup(final @NonNull String name,final @NonNull String email,final @NonNull String password,final @NonNull String passwordConfirmation,final boolean sendNewsletters){
  return Observable.just(AccessTokenEnvelope.builder().user(UserFactory.user().toBuilder().name(name).build()).accessToken("deadbeef").build());
}
