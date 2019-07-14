public final boolean match(List<MediaType> acceptedMediaTypes){
  boolean match=matchMediaType(acceptedMediaTypes);
  return (!isNegated() ? match : !match);
}
