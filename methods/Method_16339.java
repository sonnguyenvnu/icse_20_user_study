@Override public void cancelDeployed(String formId){
  Objects.requireNonNull(formId);
  DynamicFormDeployLogEntity deployed=createQuery().where(DynamicFormDeployLogEntity.formId,formId).orderByDesc(DynamicFormDeployLogEntity.deployTime).single();
  if (deployed != null) {
    createUpdate().set(DynamicFormDeployLogEntity.status,DataStatus.STATUS_DISABLED).where(DynamicFormDeployLogEntity.id,deployed.getId()).exec();
  }
}
