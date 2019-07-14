protected Function<AccessTokenInfo,OAuth2UserTokenEntity> entityTokenInfoMapping(){
  return info -> {
    OAuth2UserTokenEntity entity=entityFactory.newInstance(OAuth2UserTokenEntity.class,info);
    entity.setExpiresIn(info.getExpiresIn());
    entity.setAccessToken(info.getAccessToken());
    entity.setCreateTime(info.getCreateTime());
    entity.setUpdateTime(info.getUpdateTime());
    entity.setRefreshToken(info.getRefreshToken());
    entity.setServerId(info.getServerId());
    entity.setGrantType(info.getGrantType());
    entity.setScope(info.getScope());
    return entity;
  }
;
}
