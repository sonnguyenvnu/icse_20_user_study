@Override public JobOperateAPI getJobOperatorAPI(){
  RegistryCenterConfiguration regCenterConfig=SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
  return JobAPIFactory.createJobOperateAPI(regCenterConfig.getZkAddressList(),regCenterConfig.getNamespace(),Optional.fromNullable(regCenterConfig.getDigest()));
}
