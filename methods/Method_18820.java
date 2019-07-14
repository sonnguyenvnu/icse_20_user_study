static TypeSpecDataHolder generateEventDispatcher(EventDeclarationModel eventDeclaration){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  MethodSpec.Builder eventDispatcherMethod=MethodSpec.methodBuilder("dispatch" + eventDeclaration.name.simpleName()).addModifiers(Modifier.STATIC).addParameter(ClassNames.EVENT_HANDLER,"_eventHandler");
  eventDispatcherMethod.addStatement("final $T _eventState = new $T()",eventDeclaration.name,eventDeclaration.name);
  for (  FieldModel fieldModel : eventDeclaration.fields) {
    if (fieldModel.field.modifiers.contains(Modifier.FINAL)) {
      continue;
    }
    eventDispatcherMethod.addParameter(fieldModel.field.type,fieldModel.field.name).addStatement("_eventState.$L = $L",fieldModel.field.name,fieldModel.field.name);
  }
  eventDispatcherMethod.addStatement("$T _lifecycle = _eventHandler.mHasEventDispatcher.getEventDispatcher()",ClassNames.EVENT_DISPATCHER);
  if (eventDeclaration.returnType.equals(TypeName.VOID)) {
    eventDispatcherMethod.addStatement("_lifecycle.dispatchOnEvent(_eventHandler, _eventState)");
  }
 else {
    eventDispatcherMethod.addStatement("return ($T) _lifecycle.dispatchOnEvent(_eventHandler, _eventState)",eventDeclaration.returnType).returns(eventDeclaration.returnType);
  }
  return typeSpecDataHolder.addMethod(eventDispatcherMethod.build()).build();
}
