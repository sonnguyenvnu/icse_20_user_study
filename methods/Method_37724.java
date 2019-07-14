public static LocalDateTime fromCalendar(final Calendar calendar){
  final TimeZone tz=calendar.getTimeZone();
  final ZoneId zid=tz == null ? ZoneId.systemDefault() : tz.toZoneId();
  return LocalDateTime.ofInstant(calendar.toInstant(),zid);
}
