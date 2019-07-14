public CompositeHttpRequestMatcher and(HttpRequestMatcher matcher){
  this.matchers.add(matcher);
  return this;
}
