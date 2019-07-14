@Override public void cancelDeployed(String formId,long version){
  Objects.requireNonNull(formId);
  createUpdate().set(DynamicFormDeployLogEntity.status,DataStatus.STATUS_DISABLED).where(DynamicFormDeployLogEntity.formId,formId).and(DynamicFormDeployLogEntity.version,version).exec();
}
