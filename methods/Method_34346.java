private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication){
  if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
    return null;
  }
  int validitySeconds=getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
  String value=UUID.randomUUID().toString();
  if (validitySeconds > 0) {
    return new DefaultExpiringOAuth2RefreshToken(value,new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
  }
  return new DefaultOAuth2RefreshToken(value);
}
