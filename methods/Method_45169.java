public static <T>RequestMatcher startsWith(final RequestExtractor<T> extractor,final Resource resource){
  return new StartsWithMatcher<>(extractor,resource);
}
