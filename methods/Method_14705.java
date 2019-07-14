static public LocalDateTime stringToLocalDate(String s){
  OffsetDateTime parsed=stringToDate(s);
  if (parsed == null) {
    return null;
  }
  return parsed.withOffsetSameInstant(OffsetDateTime.now().getOffset()).toLocalDateTime();
}
