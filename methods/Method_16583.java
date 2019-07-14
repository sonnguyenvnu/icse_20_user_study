@Override @Cacheable(key="'name:'+#name") @Transactional(readOnly=true) public OrganizationalEntity selectByName(String name){
  if (StringUtils.isEmpty(name)) {
    return null;
  }
  return createQuery().where(OrganizationalEntity.name,name).single();
}
