@Override public String cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  if (null != src && CharSequence.class.isAssignableFrom(src.getClass().getComponentType())) {
    return Lang.concat(",",(CharSequence[])src).toString();
  }
  return Json.toJson(src,JsonFormat.compact());
}
