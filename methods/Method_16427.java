@Override @Transactional(propagation=Propagation.NOT_SUPPORTED) @Cacheable(cacheNames="oauth2-access-token",key="'token:'+#accessToken") public OAuth2AccessToken getTokenByAccessToken(String accessToken){
  Assert.notNull(accessToken,"accessToken can not be null!");
  return DefaultDSLQueryService.createQuery(oAuth2AccessDao).where("accessToken",accessToken).single();
}
