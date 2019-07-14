@Override public void onUserChanged(int requestCode,User newUser){
  mUser=newUser;
  mSimpleUser=newUser;
  mUserIdOrUid=newUser.getIdOrUid();
  getListener().onUserChanged(getRequestCode(),newUser);
  notifyChangedIfLoaded();
  ensureResourcesIfHasSimpleUser();
}
