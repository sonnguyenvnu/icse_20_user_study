public final NormalizedCacheFactory<T> chain(@NotNull NormalizedCacheFactory factory){
  checkNotNull(factory,"factory == null");
  NormalizedCacheFactory leafFactory=this;
  while (leafFactory.nextFactory.isPresent()) {
    leafFactory=(NormalizedCacheFactory)leafFactory.nextFactory.get();
  }
  leafFactory.nextFactory=Optional.of(factory);
  return this;
}
