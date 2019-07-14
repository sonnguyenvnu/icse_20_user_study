private <R extends TokenRequest>DefaultOAuth2Granter addGranter(String grantType,Class<R> tokenRequestType,Function<R,OAuth2AccessToken> granterService){
  supportGranter.put(grantType,Granter.build(tokenRequestType,granterService));
  return this;
}
