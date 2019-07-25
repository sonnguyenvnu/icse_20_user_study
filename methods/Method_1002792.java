/** 
 * Sets  {@link MediaType}s that a  {@link Service} will produce to be used incontent negotiation. See <a href="https://tools.ietf.org/html/rfc7231#section-5.3.2">Accept header</a> for more information.
 */
public AbstractServiceBindingBuilder produces(Iterable<MediaType> produceTypes){
  ensureUniqueMediaTypes(produceTypes,"produceTypes");
  this.produceTypes=ImmutableSet.copyOf(produceTypes);
  return this;
}
