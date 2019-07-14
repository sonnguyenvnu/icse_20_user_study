public void addDeployment(DeploymentDescriptor dd){
  deploys.add(dd);
  deployRegistry.add(dd);
  springPowered.put(dd.getModuleName(),dd);
}
