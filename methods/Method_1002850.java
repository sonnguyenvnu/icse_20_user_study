/** 
 * Sets the  {@link Route} to consume the specified {@link MediaType}s. If not set, the mapped  {@link Service} accepts {@link HttpRequest}s that have any {@link HttpHeaderNames#CONTENT_TYPE}. In order to get this work,  {@link #methods(Iterable)} must be set.
 */
public RouteBuilder consumes(MediaType... consumeTypes){
  consumes(ImmutableSet.copyOf(requireNonNull(consumeTypes,"consumeTypes")));
  return this;
}
