@Override @NotNull public <D extends Operation.Data,T,V extends Operation.Variables>ApolloStoreOperation<Response<T>> read(@NotNull final Operation<D,T,V> operation,@NotNull final ResponseFieldMapper<D> responseFieldMapper,@NotNull final ResponseNormalizer<Record> responseNormalizer,@NotNull final CacheHeaders cacheHeaders){
  checkNotNull(operation,"operation == null");
  checkNotNull(responseNormalizer,"responseNormalizer == null");
  return new ApolloStoreOperation<Response<T>>(dispatcher){
    @Override protected Response<T> perform(){
      return doRead(operation,responseFieldMapper,responseNormalizer,cacheHeaders);
    }
  }
;
}
