private static ImmutableSet<String> getAnnotationValueAsStrings(Symbol sym){
  List<Attribute.Compound> rawAttributes=sym.getRawAttributes();
  if (rawAttributes.isEmpty()) {
    return ImmutableSet.of();
  }
  return rawAttributes.stream().filter(a -> a.getAnnotationType().asElement().getSimpleName().contentEquals("GuardedBy")).flatMap(a -> MoreAnnotations.getValue(a,"value").map(MoreAnnotations::asStrings).orElse(Stream.empty())).collect(toImmutableSet());
}
