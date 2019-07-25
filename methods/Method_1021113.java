@Override public Optional<SchemaEvolutionIncompatibility> validate(final EventType original,final EventTypeBase eventType){
  if (!eventType.getCategory().equals(original.getCategory())) {
    return Optional.of(new SchemaEvolutionIncompatibility.CategoryIncompatibility("changing category is not allowed"));
  }
 else {
    return Optional.empty();
  }
}
