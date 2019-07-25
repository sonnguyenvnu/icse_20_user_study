@NotNull @Override public <D extends Operation.Data,T,V extends Operation.Variables>ApolloStoreOperation<Response<T>> read(@NotNull Operation<D,T,V> operation,@NotNull ResponseFieldMapper<D> responseFieldMapper,@NotNull ResponseNormalizer<Record> responseNormalizer,@NotNull CacheHeaders cacheHeaders){
  return ApolloStoreOperation.emptyOperation(Response.<T>builder(operation).build());
}
