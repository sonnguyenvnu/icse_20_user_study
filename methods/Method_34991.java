@Nonnull public static <I,O>ListenableFuture<O> transform(@Nonnull ListenableFuture<I> input,Function<? super I,? extends O> function){
  Preconditions.checkNotNull(function);
  Futures.ChainingFuture<I,O> output=new Futures.ChainingFuture(input,function);
  input.addListener(output,DirectExecutor.INSTANCE);
  return output;
}
