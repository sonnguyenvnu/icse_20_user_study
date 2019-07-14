/** 
 * Converts local date time to epoh milliseconds assuming start of the day as time point.
 */
public static long toMilliseconds(final LocalDate localDate){
  return toMilliseconds(localDate.atStartOfDay());
}
