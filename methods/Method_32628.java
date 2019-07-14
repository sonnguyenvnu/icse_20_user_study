/** 
 * Returns a new period with the specified number of weeks. <p> This period instance is immutable and unaffected by this method call.
 * @param weeks  the amount of weeks to add, may be negative
 * @return the new period with the increased weeks
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period withWeeks(int weeks){
  int[] values=getValues();
  getPeriodType().setIndexedField(this,PeriodType.WEEK_INDEX,values,weeks);
  return new Period(values,getPeriodType());
}
