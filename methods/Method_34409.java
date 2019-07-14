public boolean isRefreshToken(OAuth2AccessToken token){
  return token.getAdditionalInformation().containsKey(ACCESS_TOKEN_ID);
}
