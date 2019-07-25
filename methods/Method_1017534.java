@Override public SecurityConfigurer<S,E> transition(String attributes,ComparisonType match){
  if (transitionSecurityRule == null) {
    transitionSecurityRule=new SecurityRule();
  }
  transitionSecurityRule.setAttributes(SecurityRule.commaDelimitedListToSecurityAttributes(attributes));
  return this;
}
