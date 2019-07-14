public List<DeploymentDescriptor> getAllInactiveDeployments(){
  Collections.sort(inactiveDeploys);
  return inactiveDeploys;
}
