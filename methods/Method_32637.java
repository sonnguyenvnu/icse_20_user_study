/** 
 * Returns a new period plus the specified number of minutes added. <p> This period instance is immutable and unaffected by this method call.
 * @param minutes  the amount of minutes to add, may be negative
 * @return the new period plus the increased minutes
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusMinutes(int minutes){
  if (minutes == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.MINUTE_INDEX,values,minutes);
  return new Period(values,getPeriodType());
}
