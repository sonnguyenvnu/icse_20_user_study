@Override @Cacheable(key="'all-department-id:'+#personId.hashCode()") public List<String> selectAllDepartmentId(List<String> personId){
  if (CollectionUtils.isEmpty(personId)) {
    return new java.util.ArrayList<>();
  }
  List<String> positionId=DefaultDSLQueryService.createQuery(personPositionDao).where().in(PersonPositionEntity.personId,personId).listNoPaging().stream().map(PersonPositionEntity::getPositionId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
  if (CollectionUtils.isEmpty(positionId)) {
    return new java.util.ArrayList<>();
  }
  return DefaultDSLQueryService.createQuery(positionDao).where().in(PositionEntity.id,positionId).listNoPaging().stream().map(PositionEntity::getDepartmentId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
}
