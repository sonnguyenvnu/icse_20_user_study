@Override public java.sql.Time cast(String src,Class<?> toType,String... args){
  if (Strings.isBlank(src))   return null;
  return new java.sql.Time(toDate(src).getTime());
}
