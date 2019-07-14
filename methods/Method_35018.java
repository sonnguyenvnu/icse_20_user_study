void invalidateAll(@Nonnull Iterable<?> keys){
  for (  Object key : keys) {
    remove(key);
  }
}
