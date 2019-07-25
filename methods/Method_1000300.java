@Override public Object cast(Map src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Lang.map2Object(src,toType);
}
