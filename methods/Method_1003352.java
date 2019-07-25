/** 
 * Get or create a date value for the given date.
 * @param date the date
 * @return the value
 */
public static ValueDate get(Date date){
  long ms=date.getTime();
  return fromDateValue(DateTimeUtils.dateValueFromLocalMillis(ms + DateTimeUtils.getTimeZoneOffset(ms)));
}
