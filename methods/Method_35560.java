@Override public BasicMappingBuilder andMatching(ValueMatcher<Request> customMatcher){
  requestPatternBuilder.andMatching(customMatcher);
  return this;
}
