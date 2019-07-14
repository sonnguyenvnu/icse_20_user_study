@Override public @NonNull Observable<AccessTokenEnvelope> loginWithFacebook(final @NonNull String fbAccessToken,final @NonNull String code){
  return this.service.login(LoginWithFacebookBody.builder().accessToken(fbAccessToken).code(code).build()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
