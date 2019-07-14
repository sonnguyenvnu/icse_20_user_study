public OAuth2AccessToken readAccessToken(String tokenValue){
  return this.accessTokenStore.get(tokenValue);
}
