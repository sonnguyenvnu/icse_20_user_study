@Override @Caching(evict={@CacheEvict(key="'define-id:'+#entity.processDefineId"),@CacheEvict(key="'define-key-latest:'+#entity.processDefineKey")}) public int updateByPk(String id,ProcessDefineConfigEntity entity){
  entity.setUpdateTime(new Date());
  return super.updateByPk(id,entity);
}
