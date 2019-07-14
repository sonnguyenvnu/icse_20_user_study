@Override public void authenticate(OAuth2ProtectedResourceDetails resource,OAuth2ClientContext clientContext,ClientHttpRequest request){
  OAuth2AccessToken accessToken=clientContext.getAccessToken();
  if (accessToken == null) {
    throw new AccessTokenRequiredException(resource);
  }
  String tokenType=accessToken.getTokenType();
  if (!StringUtils.hasText(tokenType)) {
    tokenType=OAuth2AccessToken.BEARER_TYPE;
  }
 else   if (tokenType.equalsIgnoreCase(OAuth2AccessToken.BEARER_TYPE)) {
    tokenType=OAuth2AccessToken.BEARER_TYPE;
  }
  request.getHeaders().set("Authorization",String.format("%s %s",tokenType,accessToken.getValue()));
}
