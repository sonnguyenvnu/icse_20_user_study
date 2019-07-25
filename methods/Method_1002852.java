/** 
 * Sets the  {@link Route} to produce the specified {@link MediaType}s. If not set, the mapped  {@link Service} accepts {@link HttpRequest}s that have any {@link HttpHeaderNames#ACCEPT}. In order to get this work,  {@link #methods(Iterable)} must be set.
 */
public RouteBuilder produces(Iterable<MediaType> produceTypes){
  ensureUniqueMediaTypes(produceTypes,"produceTypes");
  produces=ImmutableSet.copyOf(produceTypes);
  return this;
}
