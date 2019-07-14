/** 
 * Returns a new period plus the specified number of weeks added. <p> This period instance is immutable and unaffected by this method call.
 * @param weeks  the amount of weeks to add, may be negative
 * @return the new period plus the increased weeks
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusWeeks(int weeks){
  if (weeks == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.WEEK_INDEX,values,weeks);
  return new Period(values,getPeriodType());
}
