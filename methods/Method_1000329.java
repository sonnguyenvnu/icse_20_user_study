@Override public Set cast(String src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Json.fromJson(Set.class,src);
}
