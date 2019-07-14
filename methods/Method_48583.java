public Set<String> getOpenInstancesInternal(){
  Set<String> openInstances=Sets.newHashSet(modifyConfig.getContainedNamespaces(REGISTRATION_NS));
  LOGGER.debug("Open instances: {}",openInstances);
  return openInstances;
}
