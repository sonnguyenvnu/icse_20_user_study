public List<DeploymentDescriptor> getResolvedDeployments(){
  if (resolvedDeployments != null) {
    return resolvedDeployments;
  }
  resolvedDeployments=deployRegistry.getResolvedObjects();
  return resolvedDeployments;
}
