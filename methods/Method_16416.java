@Override @Caching(put=@CachePut(cacheNames="oauth2-user-token",key="'a-t:'+#tokenInfo.accessToken"),evict=@CacheEvict(cacheNames="oauth2-user-token-list",key="'s-g-t:'+#result.serverId+':'+#result.grantType")) @Transactional(propagation=Propagation.NOT_SUPPORTED) public AccessTokenInfo insert(AccessTokenInfo tokenInfo){
  if (tokenInfo.getId() == null) {
    tokenInfo.setId(getIDGenerator().generate());
  }
  OAuth2UserTokenEntity entity=entityTokenInfoMapping().apply(tokenInfo);
  entity.setCreateTime(tokenInfo.getCreateTime());
  entity.setUpdateTime(tokenInfo.getUpdateTime());
  insert(entity);
  return tokenInfo;
}
