@Override public TmInfo cast(Integer src,Class<?> toType,String... args) throws FailToCastObjectException {
  if (null != src) {
    return Times.Ti(src);
  }
  return null;
}
