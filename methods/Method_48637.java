public SliceQuery getQuery(final InternalRelationType type,Direction dir){
  CacheEntry ce;
  try {
    ce=cache.get(type.longId(),() -> new CacheEntry(edgeSerializer,type));
  }
 catch (  ExecutionException e) {
    throw new AssertionError("Should not happen: " + e.getMessage());
  }
  assert ce != null;
  return ce.get(dir);
}
