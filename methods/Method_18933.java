public static boolean isPropOutput(SpecModel specModel,MethodParamModel methodParamModel){
  final PropModel prop=getPropWithName(specModel,methodParamModel.getName());
  return prop != null && methodParamModel.getTypeName() instanceof ParameterizedTypeName && ((ParameterizedTypeName)methodParamModel.getTypeName()).rawType.equals(OUTPUT) && ((ParameterizedTypeName)methodParamModel.getTypeName()).typeArguments.size() == 1 && ((ParameterizedTypeName)methodParamModel.getTypeName()).typeArguments.get(0).equals(prop.getTypeName().box());
}
