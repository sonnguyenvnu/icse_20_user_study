private static MethodSpec generateEventTriggerChangeKeyMethod(SpecModel specModel,SpecMethodModel<EventMethod,EventDeclarationModel> triggerMethodModel){
  final String eventTriggerName=ComponentBodyGenerator.getEventTriggerInstanceName(triggerMethodModel.name);
  final String implMemberName=getComponentMemberInstanceName(specModel);
  return MethodSpec.methodBuilder(getEventTriggerKeyResetMethodName(triggerMethodModel.name)).addModifiers(Modifier.PRIVATE).addParameter(ClassNames.STRING,"key").addStatement("$L $L = this.$L.$L",ClassNames.EVENT_TRIGGER,eventTriggerName,implMemberName,eventTriggerName).beginControlFlow("if ($L == null)",eventTriggerName).addStatement("$L = $L.$L(this.mContext, key)",eventTriggerName,specModel.getComponentName(),eventTriggerName).endControlFlow().addStatement("$L($L)",eventTriggerName,eventTriggerName).build();
}
