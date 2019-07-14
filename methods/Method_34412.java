@Override public OAuth2AccessToken readAccessToken(String tokenValue){
  OAuth2AccessToken accessToken=convertAccessToken(tokenValue);
  if (jwtTokenEnhancer.isRefreshToken(accessToken)) {
    throw new InvalidTokenException("Encoded token is a refresh token");
  }
  return accessToken;
}
