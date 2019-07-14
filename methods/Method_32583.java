/** 
 * Gets the years field part of the period.
 * @return the number of years in the period, zero if unsupported
 */
public int getYears(){
  return getPeriodType().getIndexedField(this,PeriodType.YEAR_INDEX);
}
