public boolean revokeToken(String tokenValue){
  OAuth2AccessToken accessToken=tokenStore.readAccessToken(tokenValue);
  if (accessToken == null) {
    return false;
  }
  if (accessToken.getRefreshToken() != null) {
    tokenStore.removeRefreshToken(accessToken.getRefreshToken());
  }
  tokenStore.removeAccessToken(accessToken);
  return true;
}
