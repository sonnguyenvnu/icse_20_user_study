@Override @Cacheable(cacheNames="oauth2-user-token-list",key="'s-g-t:'+#serverId+':'+#grantType") public List<AccessTokenInfo> findByServerIdAndGrantType(String serverId,String grantType){
  return selectByServerIdAndGrantType(serverId,grantType).stream().map(tokenInfoMapping()).collect(Collectors.toList());
}
