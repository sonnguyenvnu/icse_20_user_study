/** 
 * Returns a copy of this datetime minus the specified number of years. <p> This LocalDateTime instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> LocalDateTime subtracted = dt.minusYears(6); LocalDateTime subtracted = dt.minus(Period.years(6)); LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6); </pre>
 * @param years  the amount of years to subtract, may be negative
 * @return the new LocalDateTime minus the increased years
 */
public LocalDateTime minusYears(int years){
  if (years == 0) {
    return this;
  }
  long instant=getChronology().years().subtract(getLocalMillis(),years);
  return withLocalMillis(instant);
}
