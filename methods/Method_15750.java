@Override public void onApplicationEvent(AuthorizationExitEvent event){
  userTokenManager.signOutByToken(geToken());
}
