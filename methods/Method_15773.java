@Override public OAuth2AccessToken requestToken(AuthorizationCodeTokenRequest request){
  String clientId=request.getClientId();
  String clientSecret=request.getClientSecret();
  String code=request.getCode();
  String redirectUri=request.getRedirectUri();
  assertParameterNotBlank(clientId,ILLEGAL_CLIENT_ID);
  assertParameterNotBlank(clientSecret,ILLEGAL_CLIENT_SECRET);
  assertParameterNotBlank(code,ILLEGAL_CODE);
  assertParameterNotBlank(redirectUri,ILLEGAL_REDIRECT_URI);
  OAuth2Client client=getClient(clientId,clientSecret);
  assertGrantTypeSupport(client,GrantType.authorization_code);
  AuthorizationCode authorizationCode=authorizationCodeService.consumeAuthorizationCode(code);
  if (authorizationCode == null) {
    throw new GrantTokenException(ErrorType.ILLEGAL_CODE);
  }
  if (System.currentTimeMillis() - authorizationCode.getCreateTime() > codeTimeOut) {
    throw new GrantTokenException(ErrorType.EXPIRED_CODE);
  }
  OAuth2AccessToken accessToken=accessTokenService.createToken();
  accessToken.setGrantType(GrantType.authorization_code);
  accessToken.setScope(authorizationCode.getScope());
  accessToken.setOwnerId(authorizationCode.getUserId());
  accessToken.setExpiresIn(3600);
  accessToken.setClientId(clientId);
  return accessTokenService.saveOrUpdateToken(accessToken);
}
