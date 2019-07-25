/** 
 * A  {@link Function} to be delegated to as an {@link ItemProcessor}.  If this is set, it will take precedence over any  {@code ItemProcessor} configured via{@link #processor(ItemProcessor)}.
 * @param function the function to delegate item processing to
 * @return this for fluent chaining
 */
public SimpleStepBuilder<I,O> processor(Function<? super I,? extends O> function){
  this.itemProcessorFunction=function;
  return this;
}
