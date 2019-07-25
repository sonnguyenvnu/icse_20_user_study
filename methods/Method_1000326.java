@Override public Map cast(String src,Class<?> toType,String... args) throws FailToCastObjectException {
  return (Map)Json.fromJson(toType,src);
}
