@Override public OAuth2AccessToken requestToken(PasswordRequest request){
  String username=request.getUsername();
  String password=request.getPassword();
  Set<String> scope=request.getScope();
  assertParameterNotBlank(username,ILLEGAL_USERNAME);
  assertParameterNotBlank(password,ILLEGAL_PASSWORD);
  String userId=passwordService.getUserIdByUsernameAndPassword(username,password);
  assertParameterNotBlank(userId,USER_NOT_EXIST);
  OAuth2Client client=getClientByOwnerId(userId);
  assertGrantTypeSupport(client,GrantType.implicit);
  if (scope == null || scope.isEmpty()) {
    scope=client.getDefaultGrantScope();
  }
  if (!client.getDefaultGrantScope().containsAll(scope)) {
    throw new GrantTokenException(SCOPE_OUT_OF_RANGE);
  }
  OAuth2AccessToken accessToken=accessTokenService.createToken();
  accessToken.setGrantType(GrantType.password);
  accessToken.setScope(scope);
  accessToken.setOwnerId(userId);
  accessToken.setExpiresIn(3600);
  accessToken.setClientId(client.getId());
  OAuth2AccessToken old=accessTokenService.tryGetOldToken(accessToken);
  if (old != null && System.currentTimeMillis() - old.getUpdateTime() < 10000) {
    return old;
  }
  return accessTokenService.saveOrUpdateToken(accessToken);
}
