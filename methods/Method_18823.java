static MethodSpec generateEventHandlerFactory(SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel,TypeName paramClass){
  final MethodSpec.Builder builder=MethodSpec.methodBuilder(eventMethodModel.name.toString()).addModifiers(Modifier.PUBLIC,Modifier.STATIC).addTypeVariables(eventMethodModel.typeVariables).addParameter(paramClass,"c").returns(ParameterizedTypeName.get(ClassNames.EVENT_HANDLER,eventMethodModel.typeModel.name));
  final CodeBlock.Builder paramsBlock=CodeBlock.builder();
  paramsBlock.add("new Object[] {\n");
  paramsBlock.indent();
  paramsBlock.add("c,\n");
  for (  MethodParamModel methodParamModel : eventMethodModel.methodParams) {
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class)) {
      builder.addParameter(methodParamModel.getTypeName(),methodParamModel.getName());
      paramsBlock.add("$L,\n",methodParamModel.getName());
      if (methodParamModel.getTypeName() instanceof TypeVariableName) {
        builder.addTypeVariable((TypeVariableName)methodParamModel.getTypeName());
      }
    }
  }
  paramsBlock.unindent();
  paramsBlock.add("}");
  builder.addStatement("return newEventHandler(c, $L, $L)",eventMethodModel.name.toString().hashCode(),paramsBlock.build());
  return builder.build();
}
