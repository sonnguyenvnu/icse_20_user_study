@Override @Caching(evict={@CacheEvict(key="'define-id:'+#entity.processDefineId"),@CacheEvict(key="'define-key-latest:'+#entity.processDefineKey")}) protected int updateByPk(ProcessDefineConfigEntity entity){
  return super.updateByPk(entity);
}
