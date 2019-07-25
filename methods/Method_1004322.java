@Override @NotNull public Set<String> merge(@NotNull Collection<Record> recordSet,@NotNull CacheHeaders cacheHeaders){
  return optimisticCache.merge(checkNotNull(recordSet,"recordSet == null"),cacheHeaders);
}
