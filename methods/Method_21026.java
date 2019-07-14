@Override public @NonNull Observable<AccessTokenEnvelope> loginWithFacebook(final @NonNull String accessToken){
  return this.service.login(LoginWithFacebookBody.builder().accessToken(accessToken).build()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
