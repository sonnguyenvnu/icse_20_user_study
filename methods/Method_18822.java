static TypeSpecDataHolder generateEventHandlerFactories(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel : specModel.getEventMethods()) {
    typeSpecDataHolder.addMethod(generateEventHandlerFactory(eventMethodModel,specModel.getContextClass()));
  }
  return typeSpecDataHolder.build();
}
