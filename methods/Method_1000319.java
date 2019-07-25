@Override public Boolean cast(String src,Class<?> toType,String... args){
  if (Strings.isBlank(src))   return false;
  return Lang.parseBoolean(src);
}
