/** 
 * Gets the weeks field part of the period.
 * @return the number of weeks in the period, zero if unsupported
 */
public int getWeeks(){
  return getPeriodType().getIndexedField(this,PeriodType.WEEK_INDEX);
}
