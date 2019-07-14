@Override public List<Entry<String,DeploymentDescriptor>> getMissingRequirements(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getMissingRequirements();
}
