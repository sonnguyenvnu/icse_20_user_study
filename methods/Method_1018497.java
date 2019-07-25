public static LocalDateTime now(Clock clock){
  final Instant now=clock.instant();
  ZoneOffset offset=clock.getZone().getRules().getOffset(now);
  return ofEpochSecond(now.getEpochSecond(),now.getNano(),offset);
}
