String generatedModelSuffix(TypeElement viewElement){
  PackageModelViewSettings modelViewConfig=getModelViewConfig(viewElement);
  if (modelViewConfig == null) {
    return GeneratedModelInfo.GENERATED_MODEL_SUFFIX;
  }
  return modelViewConfig.getGeneratedModelSuffix();
}
