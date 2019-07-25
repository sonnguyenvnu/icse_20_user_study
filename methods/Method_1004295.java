@Override public <D extends Mutation.Data,T,V extends Mutation.Variables>ApolloMutationCall<T> mutate(@NotNull Mutation<D,T,V> mutation){
  return newCall(mutation).responseFetcher(ApolloResponseFetchers.NETWORK_ONLY);
}
