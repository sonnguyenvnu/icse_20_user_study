/** 
 * Parse an ISO_LOCAL_DATE_TIME formatted string into a Java Date. For backward compatibility, to support the version <= 2.8, cannot use the DateTimeFormatter.ISO_OFFSET_DATE_TIME. Instead, use the ISO8601 below format: yyyy-MM-dd'T'HH:mm:ss'Z'
 * @param s the string to be parsed
 * @return LocalDateTime or null if the parse failed
 */
static public OffsetDateTime stringToDate(String s){
  try {
    return OffsetDateTime.parse(s);
  }
 catch (  DateTimeParseException e) {
  }
  try {
    LocalDateTime localTime=LocalDateTime.parse(s);
    return OffsetDateTime.of(localTime,ZoneId.systemDefault().getRules().getOffset(localTime));
  }
 catch (  DateTimeParseException e) {
  }
  return null;
}
