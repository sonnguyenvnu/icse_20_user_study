private void setAccessTokenInfo(AccessTokenInfo accessTokenInfo){
  this.accessTokenInfo=accessTokenInfo;
  if (onTokenChange != null) {
    onTokenChange.accept(accessTokenInfo);
  }
}
