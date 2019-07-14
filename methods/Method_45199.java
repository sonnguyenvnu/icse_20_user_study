public static RequestMatcher startsWith(final Resource resource){
  return ApiUtils.startsWith(extractor(resource.id()),checkNotNull(resource,"Resource should not be null"));
}
