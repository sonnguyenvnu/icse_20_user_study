@EventListener public void handleDatabaseInit(DatabaseInitEvent event){
  RDBDatabase database=event.getDatabase();
  if (database instanceof ClusterDatabase) {
    ClusterDatabase clusterDatabase=((ClusterDatabase)database);
    clusterDatabase.setVersionChanged(meta -> {
      String formId=meta.getProperty("formId").getValue();
      if (formId != null) {
        DynamicFormColumnBindEntity entity=dynamicFormService.selectLatestDeployed(formId);
        if (null != entity) {
          formDeployService.deploy(entity.getForm(),entity.getColumns(),false);
        }
      }
    }
);
    clusterDatabase.setLatestVersionGetter(meta -> {
      String formId=meta.getProperty("formId").getValue();
      if (formId != null) {
        return dynamicFormService.selectDeployedVersion(formId);
      }
      return -1L;
    }
);
  }
}
