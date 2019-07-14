private static void maybeAddGenericTypeToStaticFunction(MethodParamModel methodParamModel,MethodSpec.Builder eventTriggerMethod){
  if (methodParamModel.getTypeName() instanceof TypeVariableName) {
    eventTriggerMethod.addTypeVariable((TypeVariableName)methodParamModel.getTypeName());
  }
}
