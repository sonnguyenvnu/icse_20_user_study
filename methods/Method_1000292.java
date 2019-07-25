@Override public Calendar cast(Date src,Class<?> toType,String... args) throws FailToCastObjectException {
  Calendar c=Calendar.getInstance();
  c.setTime(src);
  return c;
}
