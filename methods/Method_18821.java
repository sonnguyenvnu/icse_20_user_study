/** 
 * Generate a dispatchOnEvent() implementation for the component.
 */
static MethodSpec generateDispatchOnEvent(SpecModel specModel){
  final MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("dispatchOnEvent").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(TypeName.OBJECT).addParameter(ParameterSpec.builder(EVENT_HANDLER,"eventHandler",Modifier.FINAL).build()).addParameter(ParameterSpec.builder(OBJECT,"eventState",Modifier.FINAL).build());
  methodBuilder.addStatement("int id = eventHandler.id");
  methodBuilder.beginControlFlow("switch ($L)","id");
  EventCaseGenerator.builder().contextClass(specModel.getContextClass()).eventMethodModels(specModel.getEventMethods()).withErrorPropagation(specModel.getComponentClass().equals(ClassNames.COMPONENT)).writeTo(methodBuilder);
  return methodBuilder.addStatement("default:\nreturn null").endControlFlow().build();
}
