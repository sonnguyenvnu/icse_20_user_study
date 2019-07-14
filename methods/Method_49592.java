private static Map<Geo,SpatialOperation> spatialPredicates(){
  return Collections.unmodifiableMap(Stream.of(new SimpleEntry<>(Geo.WITHIN,SpatialOperation.IsWithin),new SimpleEntry<>(Geo.CONTAINS,SpatialOperation.Contains),new SimpleEntry<>(Geo.INTERSECT,SpatialOperation.Intersects),new SimpleEntry<>(Geo.DISJOINT,SpatialOperation.IsDisjointTo)).collect(Collectors.toMap(SimpleEntry::getKey,SimpleEntry::getValue)));
}
