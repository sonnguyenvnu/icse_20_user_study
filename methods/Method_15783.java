@Override public OAuth2AccessToken requestToken(ImplicitRequest request){
  String clientId=request.getClientId();
  Set<String> scope=request.getScope();
  assertParameterNotBlank(clientId,ILLEGAL_CLIENT_ID);
  OAuth2Client client=getClient(clientId);
  assertGrantTypeSupport(client,GrantType.implicit);
  if (scope == null || scope.isEmpty()) {
    scope=client.getDefaultGrantScope();
  }
  if (!client.getDefaultGrantScope().containsAll(scope)) {
    throw new GrantTokenException(SCOPE_OUT_OF_RANGE);
  }
  if (!client.getRedirectUri().equals(request.getRedirectUri())) {
    throw new GrantTokenException(ILLEGAL_REDIRECT_URI);
  }
  OAuth2AccessToken accessToken=accessTokenService.createToken();
  accessToken.setGrantType(GrantType.implicit);
  accessToken.setScope(scope);
  accessToken.setOwnerId(client.getOwnerId());
  accessToken.setExpiresIn(3600);
  accessToken.setClientId(clientId);
  OAuth2AccessToken old=accessTokenService.tryGetOldToken(accessToken);
  if (old != null && System.currentTimeMillis() - old.getUpdateTime() < 10000) {
    return old;
  }
  return accessTokenService.saveOrUpdateToken(accessToken);
}
