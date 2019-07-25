@Override public String cast(Collection src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Json.toJson(src,JsonFormat.compact());
}
