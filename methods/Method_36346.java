@Override public List<Entry<String,DeploymentDescriptor>> getPendingEntries(){
  if (!deployments.isEmpty()) {
    commitDeployments();
  }
  return super.getPendingEntries();
}
