public static <T>RequestMatcher endsWith(final RequestExtractor<T> extractor,final Resource resource){
  return new EndsWithMatcher<>(extractor,resource);
}
