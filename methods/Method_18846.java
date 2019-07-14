private static MethodSpec.Builder addParametersToStaticTriggerMethods(ClassName contextClassName,SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel,MethodSpec.Builder eventTriggerMethod){
  for (int i=0, size=eventMethodModel.methodParams.size(); i < size; i++) {
    final MethodParamModel methodParamModel=eventMethodModel.methodParams.get(i);
    if (methodParamModel.getTypeName().equals(contextClassName)) {
      continue;
    }
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,FromTrigger.class)) {
      eventTriggerMethod.addParameter(methodParamModel.getTypeName(),methodParamModel.getName());
    }
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class)) {
      eventTriggerMethod.addParameter(methodParamModel.getTypeName(),methodParamModel.getName());
      maybeAddGenericTypeToStaticFunction(methodParamModel,eventTriggerMethod);
    }
  }
  return eventTriggerMethod;
}
