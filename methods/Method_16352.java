@Override @CacheEvict(allEntries=true) public List<DynamicFormColumnEntity> deleteColumn(List<String> ids){
  Objects.requireNonNull(ids);
  if (ids.isEmpty()) {
    return new java.util.ArrayList<>();
  }
  List<DynamicFormColumnEntity> oldColumns=DefaultDSLQueryService.createQuery(formColumnDao).where().in(DynamicFormColumnEntity.id,ids).listNoPaging();
  DefaultDSLDeleteService.createDelete(formColumnDao).where().in(DynamicFormDeployLogEntity.id,ids).exec();
  return oldColumns;
}
