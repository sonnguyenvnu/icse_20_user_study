private static MethodSpec generateStateSelfTriggerMethod(String componentClass,ClassName contextClassName,String scopeMethodName,SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel){
  MethodSpec.Builder triggerMethod=MethodSpec.methodBuilder(eventMethodModel.name.toString()).addModifiers(Modifier.STATIC);
  triggerMethod.addParameter(contextClassName,"c");
  addParametersToStaticTriggerMethods(contextClassName,eventMethodModel,triggerMethod);
  triggerMethod.addStatement("$L component = ($L) c.$L()",componentClass,componentClass,scopeMethodName);
  final CodeBlock.Builder eventTriggerParams=CodeBlock.builder().add("\n($T) $L",ClassNames.EVENT_TRIGGER_TARGET,"component");
  for (  MethodParamModel methodParamModel : eventMethodModel.methodParams) {
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,FromTrigger.class)) {
      eventTriggerParams.add(",\n$L",methodParamModel.getName());
      continue;
    }
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class)) {
      eventTriggerParams.add(",\n$L",methodParamModel.getName());
    }
  }
  EventDeclarationModel eventDeclaration=eventMethodModel.typeModel;
  if (eventDeclaration.returnType == null || eventDeclaration.returnType.equals(TypeName.VOID)) {
    triggerMethod.addStatement("component.$L($L)",eventMethodModel.name,eventTriggerParams.build());
  }
 else {
    triggerMethod.addStatement("return component.$L($L)",eventMethodModel.name,eventTriggerParams.build()).returns(eventDeclaration.returnType);
  }
  return triggerMethod.build();
}
