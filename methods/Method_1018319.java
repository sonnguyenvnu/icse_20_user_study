@Override public boolean contains(HttpMethod method){
  Assert.notNull(method,"HTTP method must not be null!");
  return methods.contains(method);
}
