@Override @CacheEvict(key="'conf-id:'+#id") public int updateByPk(String id,OAuth2ServerConfigEntity entity){
  return super.updateByPk(id,entity);
}
