/** 
 * Creates a function that delegates based on the specified conditions. <p> If no condition applies, the  {@code onElse} function will be delegated to.
 * @param onElse the function to delegate to if no condition matches
 * @param conditions the conditions
 * @param < I > the input type
 * @param < O > the output type
 * @return a conditional function
 * @see #conditional(Action)
 * @throws Exception any thrown by {@code conditions}
 * @since 1.5
 */
static <I,O>Function<I,O> conditional(Function<? super I,? extends O> onElse,Action<? super ConditionalSpec<I,O>> conditions) throws Exception {
  ImmutableList.Builder<ConditionalFunction.Branch<I,O>> builder=ImmutableList.builder();
  conditions.execute(new ConditionalSpec<I,O>(){
    @Override public ConditionalSpec<I,O> when(    Predicate<? super I> predicate,    Function<? super I,? extends O> function){
      builder.add(new ConditionalFunction.Branch<>(predicate,function));
      return this;
    }
  }
);
  return new ConditionalFunction<>(builder.build(),onElse);
}
