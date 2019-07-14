public DynamicFormDeployLogEntity createDeployLog(DynamicFormEntity form,List<DynamicFormColumnEntity> columns){
  DynamicFormDeployLogEntity entity=entityFactory.newInstance(DynamicFormDeployLogEntity.class);
  entity.setStatus(DataStatus.STATUS_ENABLED);
  entity.setDeployTime(System.currentTimeMillis());
  entity.setVersion(form.getVersion());
  entity.setFormId(form.getId());
  DynamicFormColumnBindEntity bindEntity=new DynamicFormColumnBindEntity();
  bindEntity.setForm(form);
  bindEntity.setColumns(columns);
  entity.setMetaData(JSON.toJSONString(bindEntity));
  return entity;
}
