/** 
 * Creates a <code>Hours</code> representing the number of whole hours between the two specified partial datetimes. <p> The two partials must contain the same fields, for example you can specify two <code>LocalTime</code> objects.
 * @param start  the start partial date, must not be null
 * @param end  the end partial date, must not be null
 * @return the period in hours
 * @throws IllegalArgumentException if the partials are null or invalid
 */
public static Hours hoursBetween(ReadablePartial start,ReadablePartial end){
  if (start instanceof LocalTime && end instanceof LocalTime) {
    Chronology chrono=DateTimeUtils.getChronology(start.getChronology());
    int hours=chrono.hours().getDifference(((LocalTime)end).getLocalMillis(),((LocalTime)start).getLocalMillis());
    return Hours.hours(hours);
  }
  int amount=BaseSingleFieldPeriod.between(start,end,ZERO);
  return Hours.hours(amount);
}
