private void outputModulesMessage(ApplicationRuntimeModel application) throws DeploymentException {
  StringBuilder stringBuilder=new StringBuilder();
  if (application.getAllInactiveDeployments().size() > 0) {
    writeMessageToStringBuilder(stringBuilder,application.getAllInactiveDeployments(),"All unactivated module list");
  }
  writeMessageToStringBuilder(stringBuilder,application.getAllDeployments(),"All activated module list");
  writeMessageToStringBuilder(stringBuilder,application.getResolvedDeployments(),"Modules that could install");
  SofaLogger.info(stringBuilder.toString());
  String errorMessage=getErrorMessageByApplicationModule(application);
  if (StringUtils.hasText(errorMessage)) {
    SofaLogger.error(errorMessage);
  }
  if (application.getDeployRegistry().getPendingEntries().size() > 0) {
    throw new DeploymentException(errorMessage.trim());
  }
}
