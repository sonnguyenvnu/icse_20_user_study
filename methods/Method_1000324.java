@Override public java.util.Date cast(String src,Class<?> toType,String... args){
  if (Strings.isBlank(src))   return null;
  return toDate(src);
}
