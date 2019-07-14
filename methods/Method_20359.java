PackageModelViewSettings getModelViewConfig(Element viewElement){
  String packageName=elementUtils.getPackageOf(viewElement).getQualifiedName().toString();
  return getObjectFromPackageMap(modelViewNamingMap,packageName,null);
}
