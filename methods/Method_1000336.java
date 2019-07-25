@Override public String cast(Timestamp src,Class<?> toType,String... args){
  return Times.sDT(Times.D(src.getTime()));
}
