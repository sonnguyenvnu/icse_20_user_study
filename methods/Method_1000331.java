@Override public Timestamp cast(String src,Class<?> toType,String... args){
  if (Strings.isBlank(src))   return null;
  return new java.sql.Timestamp(toDate(src).getTime());
}
