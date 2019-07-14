@Override public String propertyErrorFor(Rule rule){
  List<V> realValues=rule.getProperty(this);
  return realValues == null ? null : errorFor(realValues);
}
