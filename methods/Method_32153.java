/** 
 * Creates a <code>Days</code> representing the number of whole days in the specified interval. This method correctly handles any daylight savings time changes that may occur during the interval.
 * @param interval  the interval to extract days from, null returns zero
 * @return the period in days
 * @throws IllegalArgumentException if the partials are null or invalid
 */
public static Days daysIn(ReadableInterval interval){
  if (interval == null) {
    return Days.ZERO;
  }
  int amount=BaseSingleFieldPeriod.between(interval.getStart(),interval.getEnd(),DurationFieldType.days());
  return Days.days(amount);
}
