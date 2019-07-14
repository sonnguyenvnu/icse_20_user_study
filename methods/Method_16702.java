@Override @Caching(evict={@CacheEvict(key="'define-id:'+#entity.processDefineId+'-'+#entity.activityId"),@CacheEvict(key="'define-key:'+#entity.processDefineKey+'-'+#entity.activityId")}) public String insert(ActivityConfigEntity entity){
  entity.setCreateTime(new Date());
  entity.setUpdateTime(new Date());
  entity.setStatus(DataStatus.STATUS_ENABLED);
  return super.insert(entity);
}
