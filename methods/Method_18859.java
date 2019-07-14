public static boolean isOptionalParamValid(SpecModel specModel,ImmutableList<OptionalParameterType> parameterTypes,MethodParamModel methodParamModel){
  for (  OptionalParameterType optionalParameterType : parameterTypes) {
    if (isParamOfType(specModel,optionalParameterType,methodParamModel)) {
      return true;
    }
  }
  return false;
}
