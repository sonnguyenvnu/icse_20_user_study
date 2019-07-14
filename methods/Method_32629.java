/** 
 * Returns a new period with the specified number of hours. <p> This period instance is immutable and unaffected by this method call.
 * @param hours  the amount of hours to add, may be negative
 * @return the new period with the increased hours
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period withHours(int hours){
  int[] values=getValues();
  getPeriodType().setIndexedField(this,PeriodType.HOUR_INDEX,values,hours);
  return new Period(values,getPeriodType());
}
