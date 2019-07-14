/** 
 * Parse date string.
 * @param date the date
 * @return the string
 */
public static String parseDate(Date date){
  Instant instant=date.toInstant();
  ZoneId zone=ZoneId.systemDefault();
  LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zone);
  return formatLocalDateTime(localDateTime);
}
