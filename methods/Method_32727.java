/** 
 * Creates a <code>Weeks</code> representing the number of whole weeks between the two specified partial datetimes. <p> The two partials must contain the same fields, for example you can specify two <code>LocalDate</code> objects.
 * @param start  the start partial date, must not be null
 * @param end  the end partial date, must not be null
 * @return the period in weeks
 * @throws IllegalArgumentException if the partials are null or invalid
 */
public static Weeks weeksBetween(ReadablePartial start,ReadablePartial end){
  if (start instanceof LocalDate && end instanceof LocalDate) {
    Chronology chrono=DateTimeUtils.getChronology(start.getChronology());
    int weeks=chrono.weeks().getDifference(((LocalDate)end).getLocalMillis(),((LocalDate)start).getLocalMillis());
    return Weeks.weeks(weeks);
  }
  int amount=BaseSingleFieldPeriod.between(start,end,ZERO);
  return Weeks.weeks(amount);
}
