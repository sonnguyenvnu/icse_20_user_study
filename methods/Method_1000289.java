@Override public Timestamp cast(Calendar src,Class<?> toType,String... args){
  long ms=src.getTimeInMillis();
  return new Timestamp(ms);
}
