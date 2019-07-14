@Override public boolean match(final Request request){
  Optional<T> extractContent=extractor.extract(request);
  return extractContent.isPresent();
}
