@Override public String cast(Enum src,Class<?> toType,String... args) throws FailToCastObjectException {
  return src.name();
}
