@Override public <T>Optional<List<T>> asList(Class<T> t){
  return getNativeValue().map(v -> JSON.parseArray(v,t));
}
