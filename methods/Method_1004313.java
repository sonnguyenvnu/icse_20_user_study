@NotNull @Override public <D extends Operation.Data,T,V extends Operation.Variables>ApolloStoreOperation<T> read(@NotNull Operation<D,T,V> operation){
  return ApolloStoreOperation.emptyOperation(null);
}
