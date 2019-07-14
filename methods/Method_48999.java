public Direction getVertexCentricDirection(){
  final RelationCache cache=data.getCache();
  Preconditions.checkNotNull(cache,"Cache is null");
  return cache.direction;
}
