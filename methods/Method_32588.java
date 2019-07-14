/** 
 * Gets the minutes field part of the period.
 * @return the number of minutes in the period, zero if unsupported
 */
public int getMinutes(){
  return getPeriodType().getIndexedField(this,PeriodType.MINUTE_INDEX);
}
