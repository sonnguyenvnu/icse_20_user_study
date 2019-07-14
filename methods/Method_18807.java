static String getImplAccessor(SpecModel specModel,MethodParamModel methodParamModel,boolean shallow){
  if (methodParamModel instanceof StateParamModel || SpecModelUtils.getStateValueWithName(specModel,methodParamModel.getName()) != null) {
    return STATE_CONTAINER_FIELD_NAME + "." + methodParamModel.getName();
  }
 else   if (methodParamModel instanceof CachedValueParamModel) {
    return "get" + methodParamModel.getName().substring(0,1).toUpperCase() + methodParamModel.getName().substring(1) + "()";
  }
 else   if (methodParamModel instanceof PropModel && ((PropModel)methodParamModel).isDynamic() && !shallow) {
    return "retrieveValue(" + methodParamModel.getName() + ")";
  }
  return methodParamModel.getName();
}
