@NotNull @Override public ApolloStoreOperation<Set<String>> write(@NotNull GraphqlFragment fragment,@NotNull CacheKey cacheKey,@NotNull Operation.Variables variables){
  return ApolloStoreOperation.emptyOperation(Collections.<String>emptySet());
}
