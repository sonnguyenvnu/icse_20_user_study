private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication,OAuth2RefreshToken refreshToken){
  DefaultOAuth2AccessToken token=new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
  int validitySeconds=getAccessTokenValiditySeconds(authentication.getOAuth2Request());
  if (validitySeconds > 0) {
    token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
  }
  token.setRefreshToken(refreshToken);
  token.setScope(authentication.getOAuth2Request().getScope());
  return accessTokenEnhancer != null ? accessTokenEnhancer.enhance(token,authentication) : token;
}
