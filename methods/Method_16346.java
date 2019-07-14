@Transactional(propagation=Propagation.NOT_SUPPORTED) public void deployFromLog(DynamicFormDeployLogEntity logEntity){
  DynamicFormColumnBindEntity entity=JSON.parseObject(logEntity.getMetaData(),DynamicFormColumnBindEntity.class);
  DynamicFormEntity form=entity.getForm();
  List<DynamicFormColumnEntity> columns=entity.getColumns();
  if (logger.isDebugEnabled()) {
    logger.debug("do deploy form {} , columns size:{}",form.getName(),columns.size());
  }
  deploy(form,columns,!(loadOnlyTags != null && Arrays.asList(loadOnlyTags).contains(entity.getForm().getTags())));
}
