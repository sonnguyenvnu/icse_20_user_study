private static MethodSpec generateEventDeclarationBuilderMethod(SpecModel specModel,EventDeclarationModel eventDeclaration){
  final String eventHandlerName=ComponentBodyGenerator.getEventHandlerInstanceName(eventDeclaration.name);
  return MethodSpec.methodBuilder(eventHandlerName).addModifiers(Modifier.PUBLIC).returns(getBuilderType(specModel)).addParameter(ClassNames.EVENT_HANDLER,eventHandlerName).addStatement("this.$L.$L = $L",getComponentMemberInstanceName(specModel),eventHandlerName,eventHandlerName).addStatement("return this").build();
}
