@Override public Time cast(Timestamp src,Class<?> toType,String... args) throws FailToCastObjectException {
  return new Time(src.getTime());
}
