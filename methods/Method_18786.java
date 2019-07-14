private static MethodSpec generateKeySetterMethod(SpecModel specModel,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> triggerMethods){
  MethodSpec.Builder builder=MethodSpec.methodBuilder("key").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter(ClassNames.STRING,"key").addStatement("super.key(key)");
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> triggerMethod : triggerMethods) {
    builder.addStatement("$L(key)",getEventTriggerKeyResetMethodName(triggerMethod.name));
  }
  builder.addStatement("return this").returns(getBuilderType(specModel));
  return builder.build();
}
