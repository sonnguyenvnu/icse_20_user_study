@Override public Timestamp cast(Date src,Class<?> toType,String... args) throws FailToCastObjectException {
  return new Timestamp(src.getTime());
}
