@Override @NotNull public <D extends Operation.Data,T,V extends Operation.Variables>ApolloStoreOperation<Set<String>> write(@NotNull final Operation<D,T,V> operation,@NotNull final D operationData){
  checkNotNull(operation,"operation == null");
  checkNotNull(operationData,"operationData == null");
  return new ApolloStoreOperation<Set<String>>(dispatcher){
    @Override protected Set<String> perform(){
      return doWrite(operation,operationData,false,null);
    }
  }
;
}
