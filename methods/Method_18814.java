/** 
 * We consider an optional parameter as something that comes immediately after defined parameters and is not a special litho parameter (like a prop, state, etc...). This method verifies that optional parameters are the right type and have no additional annotations.
 */
private static boolean shouldIncludeOptionalParameter(MethodParamModel methodParamModel,MethodParamModel extraOptionalParameter){
  return methodParamModel instanceof SimpleMethodParamModel && methodParamModel.getTypeName().equals(extraOptionalParameter.getTypeName()) && methodParamModel.getAnnotations().isEmpty();
}
