@Override public Collection cast(String src,Class<?> toType,String... args) throws FailToCastObjectException {
  return (Collection)Json.fromJson(toType,Lang.inr(src));
}
