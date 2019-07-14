private RequestMatcher createCompositeMatcher(final String name,final Map<String,Object> collection){
  ImmutableList<RequestMatcher> matchers=from(collection.entrySet()).transform(toTargetMatcher(getExtractorMethod(name))).toList();
  return wrapRequestMatcher(null,matchers);
}
