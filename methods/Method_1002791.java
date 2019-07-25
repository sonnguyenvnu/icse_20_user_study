/** 
 * Sets  {@link MediaType}s that a  {@link Service} will consume. If not set, the {@link Service}will accept all media types.
 */
public AbstractServiceBindingBuilder consumes(Iterable<MediaType> consumeTypes){
  ensureUniqueMediaTypes(consumeTypes,"consumeTypes");
  this.consumeTypes=ImmutableSet.copyOf(consumeTypes);
  return this;
}
