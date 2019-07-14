@Override @Cacheable(key="'conf-id:'+#id") public OAuth2ServerConfig findById(String id){
  OAuth2ServerConfigEntity entity=selectByPk(id);
  if (null == entity) {
    return null;
  }
  return entityFactory.newInstance(OAuth2ServerConfig.class,entity);
}
