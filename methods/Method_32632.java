/** 
 * Returns a new period with the specified number of years added. <p> This period instance is immutable and unaffected by this method call.
 * @param years  the amount of years to add, may be negative
 * @return the new period with the increased years
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period plusYears(int years){
  if (years == 0) {
    return this;
  }
  int[] values=getValues();
  getPeriodType().addIndexedField(this,PeriodType.YEAR_INDEX,values,years);
  return new Period(values,getPeriodType());
}
