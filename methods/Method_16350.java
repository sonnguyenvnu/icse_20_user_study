private String saveOrUpdate0(DynamicFormColumnEntity columnEntity){
  if (StringUtils.isEmpty(columnEntity.getId()) || DefaultDSLQueryService.createQuery(formColumnDao).where(DynamicFormColumnEntity.id,columnEntity.getId()).total() == 0) {
    if (StringUtils.isEmpty(columnEntity.getId())) {
      columnEntity.setId(getIDGenerator().generate());
    }
    tryValidate(columnEntity,CreateGroup.class);
    formColumnDao.insert(columnEntity);
  }
 else {
    tryValidate(columnEntity,UpdateGroup.class);
    DefaultDSLUpdateService.createUpdate(formColumnDao,columnEntity).where(DynamicFormColumnEntity.id,columnEntity.getId()).exec();
  }
  return columnEntity.getId();
}
