static public void addPaths(String bundleName,ButterflyModule module,String[] paths){
  ClientSideResourceBundle bundle=s_bundles.get(bundleName);
  if (bundle == null) {
    bundle=new ClientSideResourceBundle();
    s_bundles.put(bundleName,bundle);
  }
  for (  String path : paths) {
    String fullPath=resolve(module,path);
    if (fullPath == null) {
      logger.error("Failed to add paths to unmounted module " + module.getName());
      break;
    }
    if (!bundle._pathSet.contains(fullPath)) {
      QualifiedPath qualifiedPath=new QualifiedPath();
      qualifiedPath.module=module;
      qualifiedPath.path=path;
      qualifiedPath.fullPath=fullPath;
      bundle._pathSet.add(fullPath);
      bundle._pathList.add(qualifiedPath);
    }
  }
}
