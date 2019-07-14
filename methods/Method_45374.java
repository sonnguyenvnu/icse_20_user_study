private Optional<RequestMatcher> createRequestMatcher(final Object result){
  if (RequestMatcher.class.isInstance(result)) {
    return Optional.of(RequestMatcher.class.cast(result));
  }
  if (ContentResource.class.isInstance(result)) {
    return Optional.of(by(ContentResource.class.cast(result)));
  }
  return Optional.absent();
}
