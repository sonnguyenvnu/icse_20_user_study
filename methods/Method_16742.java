@Override @Caching(evict={@CacheEvict(key="'define-id:'+#result.processDefineId"),@CacheEvict(key="'define-key-latest:'+#result.processDefineKey")}) public ProcessDefineConfigEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
