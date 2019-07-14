@Override public Entry<String,DeploymentDescriptor> getEntry(String key){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getEntry(key);
}
