@Override @Cacheable(key="'by-position-ids:'+#positionId.hashCode()") public List<PersonEntity> selectByPositionIds(List<String> positionId){
  if (CollectionUtils.isEmpty(positionId)) {
    return new ArrayList<>();
  }
  return createQuery().where(PersonEntity.id,"person-in-position",positionId).listNoPaging();
}
