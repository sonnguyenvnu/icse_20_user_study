@Override public OAuth2AccessToken createToken(){
  OAuth2AccessEntity accessEntity=entityFactory.newInstance(OAuth2AccessEntity.class);
  accessEntity.setAccessToken(tokenGenerator.generate());
  accessEntity.setRefreshToken(tokenGenerator.generate());
  accessEntity.setCreateTime(System.currentTimeMillis());
  return accessEntity;
}
