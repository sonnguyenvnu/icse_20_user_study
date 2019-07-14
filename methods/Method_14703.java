static public String localDateToString(LocalDateTime d){
  OffsetDateTime odt=OffsetDateTime.of(d,OffsetDateTime.now().getOffset());
  return odt.withOffsetSameInstant(ZoneOffset.of("Z")).format(ISO8601);
}
