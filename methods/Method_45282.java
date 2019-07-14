@Override protected RequestMatcher getBaseRequestMatcher(final RestIdMatcher resourceName){
  return by(uri(resourceRoot(resourceName.resourceUri())));
}
