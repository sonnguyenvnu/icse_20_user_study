private PackageConfigSettings getConfigurationForElement(Element element){
  return getConfigurationForPackage(elementUtils.getPackageOf(element));
}
