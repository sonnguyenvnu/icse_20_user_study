@Override public Object cast(String src,Class<?> toType,String... args) throws FailToCastObjectException {
  if (Strings.isQuoteByIgnoreBlank(src,'{','}'))   return Json.fromJson(toType,src);
  return Mirror.me(toType).born(src);
}
