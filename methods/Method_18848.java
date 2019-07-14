private static MethodSpec.Builder addTriggerParams(ClassName contextClassName,SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel,MethodSpec.Builder eventTriggerMethod,CodeBlock.Builder paramsBlock){
  for (int i=0, size=eventMethodModel.methodParams.size(); i < size; i++) {
    final MethodParamModel methodParamModel=eventMethodModel.methodParams.get(i);
    if (methodParamModel.getTypeName().equals(contextClassName)) {
      continue;
    }
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,FromTrigger.class)) {
      eventTriggerMethod.addStatement("_eventState.$L = $L",methodParamModel.getName(),methodParamModel.getName());
    }
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class)) {
      paramsBlock.add("$L,\n",methodParamModel.getName());
    }
  }
  return eventTriggerMethod;
}
