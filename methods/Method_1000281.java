@Override public Object cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Lang.array2array(src,toType.getComponentType());
}
