@Override @Transactional(readOnly=true) @Cacheable(key="'code:'+#code") public OrganizationalEntity selectByCode(String code){
  if (StringUtils.isEmpty(code)) {
    return null;
  }
  return createQuery().where(OrganizationalEntity.code,code).single();
}
