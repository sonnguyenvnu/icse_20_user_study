@Override public @NonNull Observable<AccessTokenEnvelope> signup(final @NonNull String name,final @NonNull String email,final @NonNull String password,final @NonNull String passwordConfirmation,final boolean sendNewsletters){
  return this.service.signup(SignupBody.builder().name(name).email(email).password(password).passwordConfirmation(passwordConfirmation).sendNewsletters(sendNewsletters).newsletterOptIn(sendNewsletters).build()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
