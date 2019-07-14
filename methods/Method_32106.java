/** 
 * Returns a copy of this datetime plus the specified number of years. <p> The calculation will do its best to only change the year field retaining the same month of year. However, in certain circumstances, it may be necessary to alter smaller fields. For example, 2008-02-29 plus one year cannot result in 2009-02-29, so the day of month is adjusted to 2009-02-28. <p> The following three lines are identical in effect: <pre> DateTime added = dt.plusYears(6); DateTime added = dt.plus(Period.years(6)); DateTime added = dt.withFieldAdded(DurationFieldType.years(), 6); </pre> <p> This datetime instance is immutable and unaffected by this method call.
 * @param years  the amount of years to add, may be negative
 * @return the new datetime plus the increased years
 * @since 1.1
 */
public DateTime plusYears(int years){
  if (years == 0) {
    return this;
  }
  long instant=getChronology().years().add(getMillis(),years);
  return withMillis(instant);
}
