private OAuth2RefreshToken createRefreshToken(OAuth2AccessToken encodedRefreshToken){
  if (!jwtTokenEnhancer.isRefreshToken(encodedRefreshToken)) {
    throw new InvalidTokenException("Encoded token is not a refresh token");
  }
  if (encodedRefreshToken.getExpiration() != null) {
    return new DefaultExpiringOAuth2RefreshToken(encodedRefreshToken.getValue(),encodedRefreshToken.getExpiration());
  }
  return new DefaultOAuth2RefreshToken(encodedRefreshToken.getValue());
}
