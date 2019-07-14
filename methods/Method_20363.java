private PackageConfigSettings getConfigurationForPackage(PackageElement packageElement){
  String packageName=packageElement.getQualifiedName().toString();
  return getConfigurationForPackage(packageName);
}
