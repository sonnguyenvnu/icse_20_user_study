public static <A,B,C,D>Connection<A> create(NullValuedFunction<A,B> aToB,Function<C,D> cToD,Connectable<B,C> connectable,Consumer<D> output){
  return new DisconnectOnNullDimapConnection<>(checkNotNull(aToB),checkNotNull(cToD),checkNotNull(connectable),checkNotNull(output));
}
