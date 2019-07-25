@Override public LocalTransitionConfigurer<S,E> secured(String expression){
  setSecurityRule(expression);
  return this;
}
