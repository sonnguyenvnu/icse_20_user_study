/** 
 * Creates a <code>Weeks</code> representing the number of whole weeks in the specified interval.
 * @param interval  the interval to extract weeks from, null returns zero
 * @return the period in weeks
 * @throws IllegalArgumentException if the partials are null or invalid
 */
public static Weeks weeksIn(ReadableInterval interval){
  if (interval == null) {
    return Weeks.ZERO;
  }
  int amount=BaseSingleFieldPeriod.between(interval.getStart(),interval.getEnd(),DurationFieldType.weeks());
  return Weeks.weeks(amount);
}
