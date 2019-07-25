@Override public SecurityConfigurer<S,E> transition(String expression){
  if (transitionSecurityRule == null) {
    transitionSecurityRule=new SecurityRule();
  }
  transitionSecurityRule.setExpression(expression);
  return this;
}
