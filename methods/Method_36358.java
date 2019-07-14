private ConfigurableApplicationContext getSpringParent(DeploymentDescriptor deployment,ApplicationRuntimeModel application){
  ConfigurableApplicationContext parentSpringContext=null;
  if (deployment.getSpringParent() != null) {
    String springParent=deployment.getSpringParent();
    if (StringUtils.hasText(springParent)) {
      DeploymentDescriptor parent=application.getSpringPoweredDeployment(springParent);
      if (parent != null) {
        parentSpringContext=(ConfigurableApplicationContext)parent.getApplicationContext();
        if (parentSpringContext == null) {
          SofaLogger.warn("Module [{0}]'s Spring-Parent [{1}] is Null!",deployment.getModuleName(),springParent);
        }
      }
    }
  }
  return parentSpringContext;
}
