/** 
 * Returns a new period plus the specified number of hours added. <p> This period instance is immutable and unaffected by this method call.
 * @param hours  the amount of hours to add, may be negative
 * @return the new period plus the increased hours
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusHours(int hours){
  if (hours == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.HOUR_INDEX,values,hours);
  return new Period(values,getPeriodType());
}
