@Override public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
  Collection<ConfigAttribute> co=new ArrayList<>();
  co.add(new SecurityConfig("null"));
  return co;
}
