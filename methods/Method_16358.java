public void deploy(DynamicFormEntity form,List<DynamicFormColumnEntity> columns,boolean updateMeta){
  RDBDatabase database=StringUtils.isEmpty(form.getDataSourceId()) ? databaseRepository.getDefaultDatabase(form.getDatabaseName()) : databaseRepository.getDatabase(form.getDataSourceId(),form.getDatabaseName());
  initDatabase(database);
  RDBTableMetaData metaData=buildTable(database,form,columns);
  try {
    if (!database.getMeta().getParser().tableExists(metaData.getName())) {
      database.createTable(metaData);
    }
 else {
      if (!updateMeta) {
        database.reloadTable(metaData);
      }
 else {
        database.alterTable(metaData);
      }
    }
  }
 catch (  SQLException e) {
    throw new DynamicFormException("????:" + e.getMessage(),e);
  }
}
