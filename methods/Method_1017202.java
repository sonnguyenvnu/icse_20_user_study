private Stream<Series> lookup(final Filter filter,final OptionalLimit limit){
  final Stream<Series> series=lookupFilter(filter);
  return limit.asLong().map(series::limit).orElse(series);
}
