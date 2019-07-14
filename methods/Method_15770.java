@Override public OAuth2AccessToken requestToken(ClientCredentialRequest request){
  String clientId=request.getClientId();
  String clientSecret=request.getClientSecret();
  assertParameterNotBlank(clientId,ILLEGAL_CLIENT_ID);
  assertParameterNotBlank(clientSecret,ILLEGAL_CLIENT_SECRET);
  OAuth2Client client=getClient(clientId,clientSecret);
  assertGrantTypeSupport(client,GrantType.client_credentials);
  OAuth2AccessToken accessToken=accessTokenService.createToken();
  accessToken.setOwnerId(client.getOwnerId());
  accessToken.setExpiresIn(3600);
  accessToken.setScope(client.getDefaultGrantScope());
  accessToken.setClientId(client.getId());
  accessToken.setGrantType(GrantType.client_credentials);
  OAuth2AccessToken old=accessTokenService.tryGetOldToken(accessToken);
  if (old != null && System.currentTimeMillis() - old.getUpdateTime() < 10000) {
    return old;
  }
  return accessTokenService.saveOrUpdateToken(accessToken);
}
