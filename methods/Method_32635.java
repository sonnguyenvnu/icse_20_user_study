/** 
 * Returns a new period plus the specified number of days added. <p> This period instance is immutable and unaffected by this method call.
 * @param days  the amount of days to add, may be negative
 * @return the new period plus the increased days
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusDays(int days){
  if (days == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.DAY_INDEX,values,days);
  return new Period(values,getPeriodType());
}
