@Override @NotNull public Collection<Record> read(@NotNull Collection<String> keys,@NotNull CacheHeaders cacheHeaders){
  return optimisticCache.loadRecords(checkNotNull(keys,"keys == null"),cacheHeaders);
}
