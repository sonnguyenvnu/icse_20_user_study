/** 
 * A field is created to save a reference to the model we create. Before the new buildModels phase we check that it is the same object as on the controller, validating that the user has not manually assigned a new model to the AutoModel field.
 */
private Iterable<FieldSpec> buildFieldsToSaveModelsForValidation(ControllerClassInfo controllerInfo){
  List<FieldSpec> fields=new ArrayList<>();
  for (  ControllerModelField model : controllerInfo.getModels()) {
    fields.add(FieldSpec.builder(getClassName(UNTYPED_EPOXY_MODEL_TYPE),model.getFieldName(),Modifier.PRIVATE).build());
  }
  return fields;
}
