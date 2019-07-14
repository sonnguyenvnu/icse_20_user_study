@Override @CacheEvict(key="'conf-id:'+#id") public OAuth2ServerConfigEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
