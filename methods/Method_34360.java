public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication){
  String key=authenticationKeyGenerator.extractKey(authentication);
  OAuth2AccessToken accessToken=authenticationToAccessTokenStore.get(key);
  if (accessToken != null && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
    storeAccessToken(accessToken,authentication);
  }
  return accessToken;
}
