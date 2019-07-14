/** 
 * We consider an optional parameter as something that comes immediately after defined parameters and is not a special litho parameter (like a prop, state, etc...).
 */
private static boolean isOptionalParameter(MethodParamModel methodParamModel,ImmutableList<MethodParamModel> extraOptionalParameters){
  for (  MethodParamModel extraOptionalParameter : extraOptionalParameters) {
    if (methodParamModel instanceof SimpleMethodParamModel && methodParamModel.getTypeName().equals(extraOptionalParameter.getTypeName()) && methodParamModel.getAnnotations().isEmpty()) {
      return true;
    }
  }
  return false;
}
