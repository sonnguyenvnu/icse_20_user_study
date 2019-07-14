public void setAccessToken(OAuth2AccessToken accessToken){
  this.accessToken=accessToken;
  this.accessTokenRequest.setExistingToken(accessToken);
}
