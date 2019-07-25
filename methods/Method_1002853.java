/** 
 * Returns a list of  {@link Router}s.
 */
@VisibleForTesting static <V>List<Router<V>> routers(Iterable<V> values,Function<V,Route> routeResolver,BiConsumer<Route,Route> rejectionHandler){
  rejectDuplicateMapping(values,routeResolver,rejectionHandler);
  final ImmutableList.Builder<Router<V>> builder=ImmutableList.builder();
  final List<V> group=new ArrayList<>();
  boolean addingTrie=true;
  for (  V value : values) {
    final Route route=routeResolver.apply(value);
    final boolean hasTriePath=route.pathType().hasTriePath();
    if (addingTrie && hasTriePath || !addingTrie && !hasTriePath) {
      group.add(value);
      continue;
    }
    if (!group.isEmpty()) {
      builder.add(router(addingTrie,group,routeResolver));
    }
    addingTrie=!addingTrie;
    group.add(value);
  }
  if (!group.isEmpty()) {
    builder.add(router(addingTrie,group,routeResolver));
  }
  return builder.build();
}
