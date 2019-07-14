@Override public Collection<Entry<String,DeploymentDescriptor>> getEntries(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getEntries();
}
