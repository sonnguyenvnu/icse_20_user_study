/** 
 * Returns a copy of this year-month minus the specified number of years. <p> This year-month instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonth subtracted = ym.minusYears(6); YearMonth subtracted = ym.minus(Period.years(6)); YearMonth subtracted = ym.withFieldAdded(DurationFieldType.years(), -6); </pre>
 * @param years  the amount of years to subtract, may be negative
 * @return the new year-month minus the increased years, never null
 */
public YearMonth minusYears(int years){
  return withFieldAdded(DurationFieldType.years(),FieldUtils.safeNegate(years));
}
