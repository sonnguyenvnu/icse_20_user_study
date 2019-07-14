@Override public void logout(){
  Timber.d("Logout current user");
  this.userPreference.delete();
  this.accessTokenPreference.delete();
  this.user.onNext(null);
  this.deviceRegistrar.unregisterDevice();
}
