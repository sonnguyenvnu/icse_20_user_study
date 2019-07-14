boolean requiresAbstractModels(TypeElement classElement){
  return globalRequireAbstractModels || getConfigurationForElement(classElement).getRequireAbstractModels();
}
