static Filter optimize(final SortedSet<Filter> filters){
  final SortedSet<Filter> result=new TreeSet<>();
  for (  final Filter f : filters) {
    if (f instanceof NotFilter) {
      if (filters.contains(((NotFilter)f).filter())) {
        return TrueFilter.get();
      }
    }
 else     if (f instanceof StartsWithFilter) {
      if (FilterUtils.containsPrefixedWith(filters,(StartsWithFilter)f,(inner,outer) -> FilterUtils.prefixedWith(outer.value(),inner.value()))) {
        continue;
      }
    }
    result.add(f);
  }
  if (result.isEmpty()) {
    return TrueFilter.get();
  }
  if (result.size() == 1) {
    return result.iterator().next();
  }
  return OrFilter.create(ImmutableList.copyOf(result));
}
