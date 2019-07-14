public String getClientId(String tokenValue){
  OAuth2Authentication authentication=tokenStore.readAuthentication(tokenValue);
  if (authentication == null) {
    throw new InvalidTokenException("Invalid access token: " + tokenValue);
  }
  OAuth2Request clientAuth=authentication.getOAuth2Request();
  if (clientAuth == null) {
    throw new InvalidTokenException("Invalid access token (no client id): " + tokenValue);
  }
  return clientAuth.getClientId();
}
