protected Function<OAuth2UserTokenEntity,AccessTokenInfo> tokenInfoMapping(){
  return entity -> {
    AccessTokenInfo info=entityFactory.newInstance(AccessTokenInfo.class,entity);
    info.setExpiresIn(entity.getExpiresIn());
    info.setAccessToken(entity.getAccessToken());
    info.setCreateTime(entity.getCreateTime());
    info.setUpdateTime(entity.getUpdateTime());
    info.setRefreshToken(entity.getRefreshToken());
    info.setServerId(entity.getServerId());
    info.setGrantType(entity.getGrantType());
    info.setScope(entity.getScope());
    return info;
  }
;
}
