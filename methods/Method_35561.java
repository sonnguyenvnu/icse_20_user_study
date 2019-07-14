@Override public BasicMappingBuilder andMatching(String customRequestMatcherName){
  requestPatternBuilder.andMatching(customRequestMatcherName);
  return this;
}
