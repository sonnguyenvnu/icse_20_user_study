@Override public String propertyErrorFor(Rule rule){
  T realValue=rule.getProperty(this);
  return realValue == null ? null : errorFor(realValue);
}
