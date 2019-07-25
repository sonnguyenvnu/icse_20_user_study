@Override public TemporalAccessor cast(Number src,Class<?> toType,String... args){
  Date date=new Date(src.longValue());
  LocalDateTime dt=LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
  if (toType == LocalDateTime.class)   return dt;
  if (toType == LocalDate.class)   return dt.toLocalDate();
  return dt.toLocalTime();
}
