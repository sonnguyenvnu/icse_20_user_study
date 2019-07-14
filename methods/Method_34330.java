public TokenRequest createTokenRequest(AuthorizationRequest authorizationRequest,String grantType){
  TokenRequest tokenRequest=new TokenRequest(authorizationRequest.getRequestParameters(),authorizationRequest.getClientId(),authorizationRequest.getScope(),grantType);
  return tokenRequest;
}
