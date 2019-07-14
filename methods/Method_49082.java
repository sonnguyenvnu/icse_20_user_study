static List<HasContainer> splitAndP(final List<HasContainer> hasContainers,final Iterable<HasContainer> has){
  has.forEach(hasContainer -> {
    if (hasContainer.getPredicate() instanceof AndP) {
      for (      final P<?> predicate : ((AndP<?>)hasContainer.getPredicate()).getPredicates()) {
        hasContainers.add(new HasContainer(hasContainer.getKey(),predicate));
      }
    }
 else     hasContainers.add(hasContainer);
  }
);
  return hasContainers;
}
