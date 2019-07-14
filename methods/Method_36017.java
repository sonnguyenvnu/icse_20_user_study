public RequestPatternBuilder andMatching(ValueMatcher<Request> customMatcher){
  this.customMatcher=customMatcher;
  return this;
}
