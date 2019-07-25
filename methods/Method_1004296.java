@Override public <D extends Mutation.Data,T,V extends Mutation.Variables>ApolloMutationCall<T> mutate(@NotNull Mutation<D,T,V> mutation,@NotNull D withOptimisticUpdates){
  checkNotNull(withOptimisticUpdates,"withOptimisticUpdate == null");
  return newCall(mutation).toBuilder().responseFetcher(ApolloResponseFetchers.NETWORK_ONLY).optimisticUpdates(Optional.<Operation.Data>fromNullable(withOptimisticUpdates)).build();
}
