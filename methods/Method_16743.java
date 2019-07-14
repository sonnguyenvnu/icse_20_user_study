@Override @Cacheable(key="'define-key-latest:'+#processDefineKey") public ProcessDefineConfigEntity selectByLatestProcessDefineKey(String processDefineKey){
  return createQuery().where("processDefineKey",Objects.requireNonNull(processDefineKey,"??[processDefineKey]????")).and("status",DataStatus.STATUS_ENABLED).orderByDesc("updateTime").single();
}
