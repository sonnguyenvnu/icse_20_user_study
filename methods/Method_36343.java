@Override public List<DeploymentDescriptor> getResolvedObjects(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getResolvedObjects();
}
