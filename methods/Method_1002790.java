/** 
 * Sets  {@link MediaType}s that a  {@link Service} will consume. If not set, the {@link Service}will accept all media types.
 */
public AbstractServiceBindingBuilder consumes(MediaType... consumeTypes){
  consumes(ImmutableSet.copyOf(requireNonNull(consumeTypes,"consumeTypes")));
  return this;
}
