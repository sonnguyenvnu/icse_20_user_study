@Override public Calendar cast(String src,Class<?> toType,String... args){
  if (Strings.isBlank(src))   return null;
  Calendar c=Calendar.getInstance();
  c.setTime(toDate(src));
  return c;
}
