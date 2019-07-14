private MethodSpec buildSaveModelsForNextValidationMethod(ControllerClassInfo controllerInfo){
  Builder builder=MethodSpec.methodBuilder("saveModelsForNextValidation").addModifiers(Modifier.PRIVATE);
  for (  ControllerModelField model : controllerInfo.getModels()) {
    builder.addStatement("$L = controller.$L",model.getFieldName(),model.getFieldName());
  }
  return builder.build();
}
