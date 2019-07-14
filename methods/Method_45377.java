private <T>RequestMatcher createRequestMatcher(final RequestExtractor<T> extractor,final Object value){
  if (TextContainer.class.isInstance(value)) {
    return getRequestMatcher(extractor,TextContainer.class.cast(value));
  }
  throw new IllegalArgumentException("unknown value type: " + value);
}
