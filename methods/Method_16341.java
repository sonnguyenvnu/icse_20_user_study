protected <T>RDBTable<T> getTable(String formId){
  DynamicFormEntity form=dynamicFormService.selectByPk(formId);
  if (null == form || Boolean.FALSE.equals(form.getDeployed())) {
    throw new NotFoundException("?????");
  }
  RDBDatabase database=StringUtils.isEmpty(form.getDataSourceId()) ? databaseRepository.getDefaultDatabase(form.getDatabaseName()) : databaseRepository.getDatabase(form.getDataSourceId(),form.getDatabaseName());
  return database.getTable(form.getDatabaseTableName());
}
