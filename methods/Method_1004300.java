public final NormalizedCache chain(@NotNull NormalizedCache cache){
  checkNotNull(cache,"cache == null");
  NormalizedCache leafCache=this;
  while (leafCache.nextCache.isPresent()) {
    leafCache=leafCache.nextCache.get();
  }
  leafCache.nextCache=Optional.of(cache);
  return this;
}
