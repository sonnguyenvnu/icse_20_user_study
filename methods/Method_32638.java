/** 
 * Returns a new period plus the specified number of seconds added. <p> This period instance is immutable and unaffected by this method call.
 * @param seconds  the amount of seconds to add, may be negative
 * @return the new period plus the increased seconds
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusSeconds(int seconds){
  if (seconds == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.SECOND_INDEX,values,seconds);
  return new Period(values,getPeriodType());
}
