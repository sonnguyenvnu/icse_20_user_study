@Override public SecurityConfigurer<S,E> event(String attributes,ComparisonType match){
  if (eventSecurityRule == null) {
    eventSecurityRule=new SecurityRule();
  }
  eventSecurityRule.setAttributes(SecurityRule.commaDelimitedListToSecurityAttributes(attributes));
  return this;
}
