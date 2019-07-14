private void writeCase(MethodSpec.Builder methodBuilder,SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel){
  methodBuilder.beginControlFlow("case $L:",eventMethodModel.name.toString().hashCode());
  final String eventVariableName="_event";
  methodBuilder.addStatement("$T $L = ($T) $L",eventMethodModel.typeModel.name,eventVariableName,eventMethodModel.typeModel.name,"eventState");
  final CodeBlock.Builder eventHandlerParams=CodeBlock.builder().indent().add("\n$L","eventHandler.mHasEventDispatcher");
  int paramIndex=0;
  for (  MethodParamModel methodParamModel : eventMethodModel.methodParams) {
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,FromEvent.class)) {
      eventHandlerParams.add(",\n($T) $L.$L",methodParamModel.getTypeName(),eventVariableName,methodParamModel.getName());
    }
 else     if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class) || methodParamModel.getTypeName().equals(mContextClass)) {
      eventHandlerParams.add(",\n($T) eventHandler.params[$L]",methodParamModel.getTypeName(),paramIndex++);
    }
  }
  eventHandlerParams.unindent();
  if (!eventMethodModel.returnType.equals(TypeName.VOID)) {
    methodBuilder.addStatement("return $L($L)",eventMethodModel.name,eventHandlerParams.build());
  }
 else {
    methodBuilder.addStatement("$L($L)",eventMethodModel.name,eventHandlerParams.build());
    methodBuilder.addStatement("return null");
  }
  methodBuilder.endControlFlow();
}
