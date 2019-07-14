@Override @Transactional(propagation=Propagation.NOT_SUPPORTED) @Cacheable(cacheNames="oauth2-access-token",key="'refresh:'+#refreshToken") public OAuth2AccessToken getTokenByRefreshToken(String refreshToken){
  Assert.notNull(refreshToken,"refreshToken can not be null!");
  return DefaultDSLQueryService.createQuery(oAuth2AccessDao).where("refreshToken",refreshToken).single();
}
