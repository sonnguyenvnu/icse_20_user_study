/** 
 * Gets the months field part of the period.
 * @return the number of months in the period, zero if unsupported
 */
public int getMonths(){
  return getPeriodType().getIndexedField(this,PeriodType.MONTH_INDEX);
}
