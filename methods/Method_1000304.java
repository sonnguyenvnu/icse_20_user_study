@Override public Boolean cast(Number src,Class<?> toType,String... args){
  return src.intValue() == 0 ? false : true;
}
