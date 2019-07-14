/** 
 * Converts local date time to Calendar.
 */
public static Calendar toCalendar(final LocalDateTime localDateTime){
  return GregorianCalendar.from(ZonedDateTime.of(localDateTime,ZoneId.systemDefault()));
}
