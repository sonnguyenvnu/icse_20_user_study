@Override public String cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  for (  Method method : Mirror.me(src).getMethods()) {
    if ("toString".equals(method.getName())) {
      return src.toString();
    }
  }
  return Json.toJson(src,JsonFormat.tidy());
}
