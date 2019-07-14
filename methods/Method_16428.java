@Override @Transactional(propagation=Propagation.NOT_SUPPORTED) @Caching(put={@CachePut(cacheNames="oauth2-access-token",key="'refresh:'+#result.refreshToken"),@CachePut(cacheNames="oauth2-access-token",key="'token:'+#result.accessToken"),@CachePut(cacheNames="oauth2-access-token",key="'cgo'+#result.clientId+#result.grantType+#result.ownerId")}) public OAuth2AccessToken saveOrUpdateToken(OAuth2AccessToken token){
  Assert.notNull(token,"token can not be null!");
  int total=DefaultDSLQueryService.createQuery(oAuth2AccessDao).where("clientId",token.getClientId()).and("grantType",token.getGrantType()).and("ownerId",token.getOwnerId()).total();
  token.setUpdateTime(System.currentTimeMillis());
  if (total > 0) {
    DefaultDSLUpdateService.createUpdate(oAuth2AccessDao,token).where("clientId",token.getClientId()).and("grantType",token.getGrantType()).and("ownerId",token.getOwnerId()).exec();
  }
 else {
    token.setCreateTime(System.currentTimeMillis());
    oAuth2AccessDao.insert(((OAuth2AccessEntity)token));
  }
  return token;
}
