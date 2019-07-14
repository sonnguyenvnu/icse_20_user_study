static TypeSpecDataHolder generateEventDispatchers(SpecModel specModel){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  for (  EventDeclarationModel eventDeclaration : specModel.getEventDeclarations()) {
    dataHolder.addTypeSpecDataHolder(generateEventDispatcher(eventDeclaration));
  }
  return dataHolder.build();
}
