@Override public InternalTransitionConfigurer<S,E> secured(String expression){
  setSecurityRule(expression);
  return this;
}
