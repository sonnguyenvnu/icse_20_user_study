/** 
 * Returns the ClassType of the given model by finding a match in the list of generated models. If no match is found the original model type is returned as a fallback.
 */
private TypeName getFullyQualifiedModelTypeName(ControllerModelField model,List<GeneratedModelInfo> generatedModels){
  String modelName=model.getTypeName().toString();
  for (  GeneratedModelInfo generatedModel : generatedModels) {
    String generatedName=generatedModel.getGeneratedName().toString();
    if (generatedName.endsWith("." + modelName)) {
      return generatedModel.getGeneratedName();
    }
  }
  return model.getTypeName();
}
