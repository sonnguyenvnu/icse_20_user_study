private void setUpParentSpringContext(GenericApplicationContext applicationContext,DeploymentDescriptor deployment,ApplicationRuntimeModel application){
  ConfigurableApplicationContext parentSpringContext=getSpringParent(deployment,application);
  if (parentSpringContext != null) {
    applicationContext.setParent(parentSpringContext);
  }
 else {
    applicationContext.setParent(this.rootApplicationContext);
  }
}
