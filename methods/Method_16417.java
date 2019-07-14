public List<OAuth2UserTokenEntity> selectByServerIdAndGrantType(String serverId,String grantType){
  Assert.notNull(serverId,"serverId can not be null!");
  Assert.notNull(grantType,"grantType can not be null!");
  return createQuery().where(OAuth2UserTokenEntity.serverId,serverId).is(OAuth2UserTokenEntity.grantType,grantType).listNoPaging();
}
