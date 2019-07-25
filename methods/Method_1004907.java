@Override public void lock(){
  if (null != groupBy) {
    groupBy=Collections.unmodifiableSet(groupBy);
  }
  transientProperties=Collections.unmodifiableMap(transientProperties);
}
