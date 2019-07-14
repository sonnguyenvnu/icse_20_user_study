public int getAccessTokenCount(){
  Assert.state(accessTokenStore.isEmpty() || accessTokenStore.size() >= accessTokenToRefreshTokenStore.size(),"Too many refresh tokens");
  Assert.state(accessTokenStore.size() == authenticationToAccessTokenStore.size(),"Inconsistent token store state");
  Assert.state(accessTokenStore.size() <= authenticationStore.size(),"Inconsistent authentication store state");
  return accessTokenStore.size();
}
