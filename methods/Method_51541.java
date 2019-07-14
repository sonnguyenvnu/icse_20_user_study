private <T>void setRulePropertyCapture(Rule rule,PropertyDescriptor<T> descriptor,String value){
  rule.setProperty(descriptor,descriptor.valueFrom(value));
}
