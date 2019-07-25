@Override public void lock(){
  if (null != parents) {
    parents=Collections.unmodifiableSet(parents);
  }
  groupBy=Collections.unmodifiableSet(groupBy);
  properties=Collections.unmodifiableMap(properties);
  identifiers=Collections.unmodifiableMap(identifiers);
  if (null != validator) {
    validator.lock();
  }
  if (null != aggregator) {
    aggregator.lock();
  }
}
