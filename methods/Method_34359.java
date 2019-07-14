public int getRefreshTokenCount(){
  Assert.state(refreshTokenStore.size() == refreshTokenToAccessTokenStore.size(),"Inconsistent refresh token store state");
  return accessTokenStore.size();
}
