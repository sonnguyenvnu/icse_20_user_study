static TypeSpecDataHolder generateEventHandlers(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  for (  EventDeclarationModel eventDeclaration : specModel.getEventDeclarations()) {
    typeSpecDataHolder.addField(FieldSpec.builder(ClassNames.EVENT_HANDLER,getEventHandlerInstanceName(eventDeclaration.name)).build());
  }
  return typeSpecDataHolder.build();
}
