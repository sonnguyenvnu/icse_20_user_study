/** 
 * Returns a new period with the specified number of seconds. <p> This period instance is immutable and unaffected by this method call.
 * @param seconds  the amount of seconds to add, may be negative
 * @return the new period with the increased seconds
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period withSeconds(int seconds){
  int[] values=getValues();
  getPeriodType().setIndexedField(this,PeriodType.SECOND_INDEX,values,seconds);
  return new Period(values,getPeriodType());
}
