@Override public Object apply(final Walk walk){
  if (null == walk) {
    throw new IllegalArgumentException("Walk cannot be null");
  }
  return walk.getSourceVertex();
}
