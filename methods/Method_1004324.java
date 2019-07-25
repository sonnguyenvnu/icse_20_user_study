@Override @NotNull public <F extends GraphqlFragment>ApolloStoreOperation<F> read(@NotNull final ResponseFieldMapper<F> responseFieldMapper,@NotNull final CacheKey cacheKey,@NotNull final Operation.Variables variables){
  checkNotNull(responseFieldMapper,"responseFieldMapper == null");
  checkNotNull(cacheKey,"cacheKey == null");
  checkNotNull(variables,"variables == null");
  return new ApolloStoreOperation<F>(dispatcher){
    @Override protected F perform(){
      return doRead(responseFieldMapper,cacheKey,variables);
    }
  }
;
}
