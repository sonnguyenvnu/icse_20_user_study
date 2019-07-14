@Override public List<Entry<String,DeploymentDescriptor>> getResolvedEntries(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getResolvedEntries();
}
