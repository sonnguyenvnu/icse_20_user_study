public static boolean isStateOutput(SpecModel specModel,MethodParamModel methodParamModel){
  final StateParamModel stateValue=SpecModelUtils.getStateValueWithName(specModel,methodParamModel.getName());
  return stateValue != null && methodParamModel.getTypeName() instanceof ParameterizedTypeName && ((ParameterizedTypeName)methodParamModel.getTypeName()).rawType.equals(OUTPUT) && ((ParameterizedTypeName)methodParamModel.getTypeName()).typeArguments.size() == 1 && ((ParameterizedTypeName)methodParamModel.getTypeName()).typeArguments.get(0).equals(stateValue.getTypeName().box());
}
