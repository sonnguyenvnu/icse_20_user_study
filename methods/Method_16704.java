@Override @Caching(evict={@CacheEvict(key="'define-id:'+#result.processDefineId+'-'+#result.activityId",condition="#result!=null"),@CacheEvict(key="'define-key:'+#result.processDefineKey+'-'+#result.activityId",condition="#result!=null")}) public ActivityConfigEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
