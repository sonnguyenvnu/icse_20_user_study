@Override public String cast(Map src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Json.toJson(src,JsonFormat.tidy());
}
