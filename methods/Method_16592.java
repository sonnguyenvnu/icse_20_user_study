@Override @Cacheable(key="'by-org-id:'+#orgId.hashCode()") public List<PersonEntity> selectByOrgId(List<String> orgId){
  if (CollectionUtils.isEmpty(orgId)) {
    return new ArrayList<>();
  }
  return createQuery().where(PersonEntity.id,"person-in-org",orgId).listNoPaging();
}
