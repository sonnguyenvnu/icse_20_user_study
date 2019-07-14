/** 
 * Returns a new period plus the specified number of millis added. <p> This period instance is immutable and unaffected by this method call.
 * @param millis  the amount of millis to add, may be negative
 * @return the new period plus the increased millis
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusMillis(int millis){
  if (millis == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.MILLI_INDEX,values,millis);
  return new Period(values,getPeriodType());
}
