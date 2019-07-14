protected OAuth2AccessToken acquireAccessToken(OAuth2ClientContext oauth2Context) throws UserRedirectRequiredException {
  AccessTokenRequest accessTokenRequest=oauth2Context.getAccessTokenRequest();
  if (accessTokenRequest == null) {
    throw new AccessTokenRequiredException("No OAuth 2 security context has been established. Unable to access resource '" + this.resource.getId() + "'.",resource);
  }
  String stateKey=accessTokenRequest.getStateKey();
  if (stateKey != null) {
    accessTokenRequest.setPreservedState(oauth2Context.removePreservedState(stateKey));
  }
  OAuth2AccessToken existingToken=oauth2Context.getAccessToken();
  if (existingToken != null) {
    accessTokenRequest.setExistingToken(existingToken);
  }
  OAuth2AccessToken accessToken=null;
  accessToken=accessTokenProvider.obtainAccessToken(resource,accessTokenRequest);
  if (accessToken == null || accessToken.getValue() == null) {
    throw new IllegalStateException("Access token provider returned a null access token, which is illegal according to the contract.");
  }
  oauth2Context.setAccessToken(accessToken);
  return accessToken;
}
