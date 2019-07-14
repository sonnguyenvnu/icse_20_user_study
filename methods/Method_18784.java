private static MethodSpec generateEventTriggerBuilderMethod(SpecModel specModel,SpecMethodModel<EventMethod,EventDeclarationModel> triggerMethodModel){
  final String eventTriggerName=ComponentBodyGenerator.getEventTriggerInstanceName(triggerMethodModel.name);
  final String implMemberName=getComponentMemberInstanceName(specModel);
  return MethodSpec.methodBuilder(eventTriggerName).addModifiers(Modifier.PUBLIC).returns(getBuilderType(specModel)).addParameter(ClassNames.EVENT_TRIGGER,eventTriggerName).addStatement("this.$L.$L = $L",implMemberName,eventTriggerName,eventTriggerName).addStatement("return this").build();
}
