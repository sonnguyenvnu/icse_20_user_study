boolean includeAlternateLayoutsForViews(TypeElement viewElement){
  PackageModelViewSettings modelViewConfig=getModelViewConfig(viewElement);
  if (modelViewConfig == null) {
    return false;
  }
  return modelViewConfig.getIncludeAlternateLayouts();
}
