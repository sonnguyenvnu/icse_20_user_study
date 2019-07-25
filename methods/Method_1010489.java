public ConceptInfo find(@NotNull SConcept concept){
  final SConceptId id=MetaIdHelper.getConcept(concept);
  assert myRegistry.containsKey(id);
  return myRegistry.get(id);
}
