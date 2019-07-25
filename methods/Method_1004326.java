@Override @NotNull public ApolloStoreOperation<Set<String>> write(@NotNull final GraphqlFragment fragment,@NotNull final CacheKey cacheKey,@NotNull final Operation.Variables variables){
  checkNotNull(fragment,"fragment == null");
  checkNotNull(cacheKey,"cacheKey == null");
  checkNotNull(variables,"operation == null");
  if (cacheKey.equals(CacheKey.NO_KEY)) {
    throw new IllegalArgumentException("undefined cache key");
  }
  return new ApolloStoreOperation<Set<String>>(dispatcher){
    @Override protected Set<String> perform(){
      return writeTransaction(new Transaction<WriteableStore,Set<String>>(){
        @Override public Set<String> execute(        WriteableStore cache){
          return doWrite(fragment,cacheKey,variables);
        }
      }
);
    }
  }
;
}
