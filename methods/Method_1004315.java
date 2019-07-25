@NotNull @Override public <F extends GraphqlFragment>ApolloStoreOperation<F> read(@NotNull ResponseFieldMapper<F> fieldMapper,@NotNull CacheKey cacheKey,@NotNull Operation.Variables variables){
  return ApolloStoreOperation.emptyOperation(null);
}
