public static LocalDateTime fromDate(final Date date){
  return LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
}
