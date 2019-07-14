static String getImplAccessor(MethodParamModel methodParamModel){
  if (methodParamModel instanceof StateParamModel) {
    return STATE_CONTAINER_FIELD_NAME + "." + methodParamModel.getName();
  }
  return methodParamModel.getName();
}
