@Override public List<String> getRequiredModules(){
  List<String> requires=new ArrayList<>();
  List<String> requireModuleIdentities=deploymentDescriptorConfiguration.getRequireModuleIdentities();
  if (requireModuleIdentities == null || requireModuleIdentities.size() == 0) {
    return requires;
  }
  for (  String requireModuleIdentity : requireModuleIdentities) {
    requires=getFormattedModuleInfo(requireModuleIdentity);
    if (!CollectionUtils.isEmpty(requires)) {
      break;
    }
  }
  String springParent=getSpringParent();
  if (springParent != null) {
    if (requires == null) {
      requires=new ArrayList<>(1);
    }
    requires.add(springParent);
  }
  return requires;
}
