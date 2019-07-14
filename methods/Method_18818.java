static TypeSpecDataHolder generateGetEventHandlerMethod(SpecModel specModel,EventDeclarationModel eventDeclaration){
  final String scopeMethodName=specModel.getScopeMethodName();
  return TypeSpecDataHolder.newBuilder().addMethod(MethodSpec.methodBuilder("get" + eventDeclaration.name.simpleName() + "Handler").addModifiers(Modifier.PUBLIC,Modifier.STATIC).returns(ClassNames.EVENT_HANDLER).addParameter(specModel.getContextClass(),"context").addCode(CodeBlock.builder().beginControlFlow("if (context.$L() == null)",scopeMethodName).addStatement("return null").endControlFlow().build()).addStatement("return (($L) context.$L()).$L",specModel.getComponentName(),scopeMethodName,ComponentBodyGenerator.getEventHandlerInstanceName(eventDeclaration.name)).build()).build();
}
