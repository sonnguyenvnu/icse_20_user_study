private void addConstraint(final JanusGraphQuery query,final List<HasContainer> localContainers){
  for (  final HasContainer condition : hasContainers) {
    query.has(condition.getKey(),JanusGraphPredicate.Converter.convert(condition.getBiPredicate()),condition.getValue());
  }
  for (  final HasContainer condition : localContainers) {
    query.has(condition.getKey(),JanusGraphPredicate.Converter.convert(condition.getBiPredicate()),condition.getValue());
  }
}
