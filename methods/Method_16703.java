@Override @Caching(evict={@CacheEvict(key="'define-id:'+#entity.processDefineId+'-'+#entity.activityId"),@CacheEvict(key="'define-key:'+#entity.processDefineKey+'-'+#entity.activityId")}) public int updateByPk(String pk,ActivityConfigEntity entity){
  entity.setUpdateTime(new Date());
  return super.updateByPk(pk,entity);
}
