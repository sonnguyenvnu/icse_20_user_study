/** 
 * Gets the hours field part of the period.
 * @return the number of hours in the period, zero if unsupported
 */
public int getHours(){
  return getPeriodType().getIndexedField(this,PeriodType.HOUR_INDEX);
}
