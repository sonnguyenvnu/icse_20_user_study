@Override @Cacheable(cacheNames="oauth2-access-token",key="'cgo'+#token.clientId+#token.grantType+#token.ownerId") public OAuth2AccessToken tryGetOldToken(OAuth2AccessToken token){
  OAuth2AccessToken old=DefaultDSLQueryService.createQuery(oAuth2AccessDao).where("clientId",token.getClientId()).and("grantType",token.getGrantType()).and("ownerId",token.getOwnerId()).single();
  return old;
}
