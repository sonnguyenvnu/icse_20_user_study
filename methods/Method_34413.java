private OAuth2AccessToken convertAccessToken(String tokenValue){
  return jwtTokenEnhancer.extractAccessToken(tokenValue,jwtTokenEnhancer.decode(tokenValue));
}
