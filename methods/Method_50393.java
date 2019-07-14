/** 
 * Gets date yyyy.
 * @return the date yyyy
 */
public static Date getDateYYYY(){
  LocalDateTime localDateTime=parseLocalDateTime(getCurrentDateTime());
  ZoneId zone=ZoneId.systemDefault();
  Instant instant=localDateTime.atZone(zone).toInstant();
  return Date.from(instant);
}
