public Health doHealthCheck(){
  Health.Builder builder=new Health.Builder();
  ApplicationRuntimeModel application=applicationContext.getBean(SofaModuleFrameworkConstants.APPLICATION,ApplicationRuntimeModel.class);
  for (  DeploymentDescriptor deploymentDescriptor : application.getInstalled()) {
    builder.withDetail(deploymentDescriptor.getName(),"passed");
  }
  for (  DeploymentDescriptor deploymentDescriptor : application.getAllInactiveDeployments()) {
    builder.withDetail(deploymentDescriptor.getName(),"inactive");
  }
  for (  DeploymentDescriptor deploymentDescriptor : application.getFailed()) {
    builder.withDetail(deploymentDescriptor.getName(),"failed");
  }
  if (application.getFailed().size() == 0) {
    return builder.status(Status.UP).build();
  }
 else {
    return builder.status(Status.DOWN).build();
  }
}
