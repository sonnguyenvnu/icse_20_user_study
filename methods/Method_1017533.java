@Override public SecurityConfigurer<S,E> event(String expression){
  if (eventSecurityRule == null) {
    eventSecurityRule=new SecurityRule();
  }
  eventSecurityRule.setExpression(expression);
  return this;
}
