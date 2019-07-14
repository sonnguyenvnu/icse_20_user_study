@Override @CacheEvict(allEntries=true) public List<String> saveOrUpdateColumn(List<DynamicFormColumnEntity> columnEntities){
  Set<String> formId=new HashSet<>();
  List<String> columnIds=columnEntities.stream().peek(columnEntity -> formId.add(columnEntity.getFormId())).map(this::saveOrUpdateColumn).collect(Collectors.toList());
  formId.forEach(getDao()::incrementVersion);
  return columnIds;
}
