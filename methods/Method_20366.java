void resolveGeneratedModelsAndWriteJava(List<GeneratedModelInfo> generatedModels){
  resolveGeneratedModelNames(controllerClassMap,generatedModels);
  generateJava(controllerClassMap);
  controllerClassMap.clear();
}
