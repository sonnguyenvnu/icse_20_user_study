@Override @Caching(evict={@CacheEvict(key="'user:'+#entity.userId+'.'+#entity.key+'.'+#entity.settingId"),@CacheEvict(key="'user:'+#entity.userId+'.'+#entity.key")}) public int updateByPk(String id,UserSettingEntity entity){
  entity.setUpdateTime(new Date());
  return super.updateByPk(id,entity);
}
