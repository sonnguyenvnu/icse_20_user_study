@Override public Timestamp cast(Number src,Class<?> toType,String... args){
  return new Timestamp(src.longValue());
}
