/** 
 * Returns a copy of this date minus the specified number of years. <p> This datetime instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonthDay subtracted = dt.minusYears(6); YearMonthDay subtracted = dt.minus(Period.years(6)); YearMonthDay subtracted = dt.withFieldAdded(DurationFieldType.years(), -6); </pre>
 * @param years  the amount of years to subtract, may be negative
 * @return the new datetime minus the increased years
 * @since 1.1
 */
public YearMonthDay minusYears(int years){
  return withFieldAdded(DurationFieldType.years(),FieldUtils.safeNegate(years));
}
