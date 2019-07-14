static boolean validJanusGraphHas(HasContainer has){
  if (has.getPredicate() instanceof ConnectiveP) {
    final List<? extends P<?>> predicates=((ConnectiveP<?>)has.getPredicate()).getPredicates();
    return predicates.stream().allMatch(p -> validJanusGraphHas(new HasContainer(has.getKey(),p)));
  }
 else {
    return JanusGraphPredicate.Converter.supports(has.getBiPredicate());
  }
}
