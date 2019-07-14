public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException, InvalidTokenException {
  OAuth2AccessToken accessToken=tokenStore.readAccessToken(accessTokenValue);
  if (accessToken == null) {
    throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
  }
 else   if (accessToken.isExpired()) {
    tokenStore.removeAccessToken(accessToken);
    throw new InvalidTokenException("Access token expired: " + accessTokenValue);
  }
  OAuth2Authentication result=tokenStore.readAuthentication(accessToken);
  if (result == null) {
    throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
  }
  if (clientDetailsService != null) {
    String clientId=result.getOAuth2Request().getClientId();
    try {
      clientDetailsService.loadClientByClientId(clientId);
    }
 catch (    ClientRegistrationException e) {
      throw new InvalidTokenException("Client not valid: " + clientId,e);
    }
  }
  return result;
}
