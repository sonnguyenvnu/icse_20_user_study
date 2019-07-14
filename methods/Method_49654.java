private static Map<Geo,String> spatialPredicates(){
  return Collections.unmodifiableMap(Stream.of(new SimpleEntry<>(Geo.WITHIN,"IsWithin"),new SimpleEntry<>(Geo.CONTAINS,"Contains"),new SimpleEntry<>(Geo.INTERSECT,"Intersects"),new SimpleEntry<>(Geo.DISJOINT,"IsDisjointTo")).collect(Collectors.toMap(SimpleEntry::getKey,SimpleEntry::getValue)));
}
