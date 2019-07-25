@Override public Object cast(String src,Class<?> toType,String... args) throws FailToCastObjectException {
  if (Strings.isQuoteByIgnoreBlank(src,'[',']')) {
    return Json.fromJson(toType,src);
  }
  String[] ss=Strings.splitIgnoreBlank(src);
  return Lang.array2array(ss,toType.getComponentType());
}
