@Override public List<DeploymentDescriptor> getPendingObjects(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getPendingObjects();
}
