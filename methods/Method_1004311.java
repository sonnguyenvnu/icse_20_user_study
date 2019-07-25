@NotNull @Override public ApolloStoreOperation<Boolean> remove(@NotNull CacheKey cacheKey,boolean cascade){
  return ApolloStoreOperation.emptyOperation(Boolean.FALSE);
}
