@Override public DynamicFormDeployLogEntity selectLastDeployed(String formId){
  Objects.requireNonNull(formId);
  DynamicFormDeployLogEntity deployed=createQuery().where(DynamicFormDeployLogEntity.formId,formId).orderByDesc(DynamicFormDeployLogEntity.deployTime).single();
  if (null != deployed && DataStatus.STATUS_ENABLED.equals(deployed.getStatus())) {
    return deployed;
  }
  return null;
}
