@Override public ExternalTransitionConfigurer<S,E> secured(String expression){
  setSecurityRule(expression);
  return this;
}
