/** 
 * Models in the same module as the controller they are used in will be processed at the same time, so the generated class won't yet exist. This means that we don't have any type information for the generated model and can't correctly import it in the generated helper class. We can resolve the FQN by looking at what models were already generated and finding matching names.
 * @param generatedModels Information about the already generated models. Relies on the modelprocessor running first and passing us this information.
 */
private void resolveGeneratedModelNames(Map<TypeElement,ControllerClassInfo> controllerClassMap,List<GeneratedModelInfo> generatedModels){
  for (  ControllerClassInfo controllerClassInfo : controllerClassMap.values()) {
    for (    ControllerModelField model : controllerClassInfo.getModels()) {
      if (!hasFullyQualifiedName(model)) {
        model.setTypeName(getFullyQualifiedModelTypeName(model,generatedModels));
      }
    }
  }
}
