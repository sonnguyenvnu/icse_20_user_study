@Override public DynamicFormDeployLogEntity selectDeployed(String formId,long version){
  Objects.requireNonNull(formId);
  DynamicFormDeployLogEntity deployed=createQuery().where(DynamicFormDeployLogEntity.formId,formId).and(DynamicFormDeployLogEntity.version,version).orderByDesc(DynamicFormDeployLogEntity.deployTime).single();
  if (null != deployed && DataStatus.STATUS_ENABLED.equals(deployed.getStatus())) {
    return deployed;
  }
  return null;
}
