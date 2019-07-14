static public OffsetDateTime calendarToOffsetDateTime(Calendar calendar){
  return calendar.toInstant().atOffset(ZoneOffset.of("Z"));
}
