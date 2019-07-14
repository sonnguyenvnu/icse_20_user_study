/** 
 * Converts local date time to Calendar and setting time to midnight.
 */
public static Calendar toCalendar(final LocalDate localDate){
  return GregorianCalendar.from(ZonedDateTime.of(localDate,LocalTime.MIDNIGHT,ZoneId.systemDefault()));
}
