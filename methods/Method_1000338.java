@Override public Integer cast(TmInfo src,Class<?> toType,String... args){
  if (null != src) {
    return src.value;
  }
  return null;
}
