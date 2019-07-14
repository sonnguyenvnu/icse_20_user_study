protected JaxbOAuth2AccessToken convertToInternal(OAuth2AccessToken accessToken){
  JaxbOAuth2AccessToken jaxbAccessToken=new JaxbOAuth2AccessToken();
  jaxbAccessToken.setAccessToken(accessToken.getValue());
  jaxbAccessToken.setExpriation(accessToken.getExpiration());
  OAuth2RefreshToken refreshToken=accessToken.getRefreshToken();
  if (refreshToken != null) {
    jaxbAccessToken.setRefreshToken(refreshToken.getValue());
  }
  return jaxbAccessToken;
}
