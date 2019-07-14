private PackageConfigSettings getConfigurationForPackage(String packageName){
  return getObjectFromPackageMap(configurationMap,packageName,DEFAULT_PACKAGE_CONFIG_SETTINGS);
}
