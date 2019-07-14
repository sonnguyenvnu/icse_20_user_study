/** 
 * Gets the days field part of the period.
 * @return the number of days in the period, zero if unsupported
 */
public int getDays(){
  return getPeriodType().getIndexedField(this,PeriodType.DAY_INDEX);
}
