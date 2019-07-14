static boolean isDataBindingModel(TypeElement type){
  return isSubtypeOfType(type.asType(),DATA_BINDING_MODEL_TYPE);
}
