/** 
 * Returns a new period plus the specified number of months added. <p> This period instance is immutable and unaffected by this method call.
 * @param months  the amount of months to add, may be negative
 * @return the new period plus the increased months
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusMonths(int months){
  if (months == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.MONTH_INDEX,values,months);
  return new Period(values,getPeriodType());
}
