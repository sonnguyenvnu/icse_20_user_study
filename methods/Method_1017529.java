@Override public LocalTransitionConfigurer<S,E> secured(String attributes,ComparisonType match){
  setSecurityRule(attributes,match);
  return this;
}
