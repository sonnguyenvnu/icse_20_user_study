static Optional<ChronoUnit> getInvalidChronoUnit(ExpressionTree tree,Iterable<ChronoUnit> invalidUnits){
  Optional<String> constant=getEnumName(tree);
  if (constant.isPresent()) {
    for (    ChronoUnit invalidTemporalUnit : invalidUnits) {
      if (constant.get().equals(invalidTemporalUnit.name())) {
        return Optional.of(invalidTemporalUnit);
      }
    }
  }
  return Optional.empty();
}
