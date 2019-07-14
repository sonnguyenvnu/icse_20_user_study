/** 
 * Returns a copy of this date plus the specified number of years. <p> This datetime instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> DateMidnight added = dt.plusYears(6); DateMidnight added = dt.plus(Period.years(6)); DateMidnight added = dt.withFieldAdded(DurationFieldType.years(), 6); </pre>
 * @param years  the amount of years to add, may be negative
 * @return the new datetime plus the increased years
 * @since 1.1
 */
public DateMidnight plusYears(int years){
  if (years == 0) {
    return this;
  }
  long instant=getChronology().years().add(getMillis(),years);
  return withMillis(instant);
}
