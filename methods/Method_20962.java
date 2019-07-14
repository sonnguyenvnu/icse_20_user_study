@Override public @NonNull Observable<AccessTokenEnvelope> loginWithFacebook(final @NonNull String accessToken){
  return Observable.just(AccessTokenEnvelope.builder().user(UserFactory.user().toBuilder().build()).accessToken("deadbeef").build());
}
