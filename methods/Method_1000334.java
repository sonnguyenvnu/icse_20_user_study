@Override public Calendar cast(Timestamp src,Class<?> toType,String... args){
  Calendar c=Calendar.getInstance();
  c.setTimeInMillis(src.getTime());
  return c;
}
