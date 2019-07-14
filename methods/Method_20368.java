/** 
 * It will have a FQN if it is from a separate library and was already compiled, otherwise if it is from this module we will just have the simple name.
 */
private boolean hasFullyQualifiedName(ControllerModelField model){
  return model.getTypeName().toString().contains(".");
}
