/** 
 * Returns a new period with the specified number of millis. <p> This period instance is immutable and unaffected by this method call.
 * @param millis  the amount of millis to add, may be negative
 * @return the new period with the increased millis
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period withMillis(int millis){
  int[] values=getValues();
  getPeriodType().setIndexedField(this,PeriodType.MILLI_INDEX,values,millis);
  return new Period(values,getPeriodType());
}
