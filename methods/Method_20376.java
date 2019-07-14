private MethodSpec buildValidateModelsHaveNotChangedMethod(ControllerClassInfo controllerInfo){
  Builder builder=MethodSpec.methodBuilder("validateModelsHaveNotChanged").addModifiers(Modifier.PRIVATE);
  long id=-1;
  for (  ControllerModelField model : controllerInfo.getModels()) {
    builder.addStatement("validateSameModel($L, controller.$L, $S, $L)",model.getFieldName(),model.getFieldName(),model.getFieldName(),id--);
  }
  return builder.addStatement("validateModelHashCodesHaveNotChanged(controller)").build();
}
