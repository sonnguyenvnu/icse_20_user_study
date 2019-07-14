@Override @Caching(evict={@CacheEvict(value="dyn-form-deploy",allEntries=true),@CacheEvict(value="dyn-form",allEntries=true)}) public void unDeploy(String formId){
  DynamicFormEntity form=selectByPk(formId);
  assertNotNull(form);
  dynamicFormDeployLogService.cancelDeployed(formId);
  RDBDatabase database=StringUtils.isEmpty(form.getDataSourceId()) ? databaseRepository.getDefaultDatabase(form.getDatabaseName()) : databaseRepository.getDatabase(form.getDataSourceId(),form.getDatabaseName());
  database.removeTable(form.getDatabaseTableName());
  createUpdate().set(DynamicFormEntity.deployed,false).where(DynamicFormEntity.id,formId).exec();
  eventPublisher.publishEvent(new FormDeployEvent(formId));
}
