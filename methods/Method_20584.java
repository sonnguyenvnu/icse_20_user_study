@Override public void login(final @NonNull User newUser,final @NonNull String accessToken){
  Timber.d("Login user %s",newUser.name());
  this.accessTokenPreference.set(accessToken);
  this.user.onNext(newUser);
  this.deviceRegistrar.registerDevice();
}
