/** 
 * Gets the seconds field part of the period.
 * @return the number of seconds in the period, zero if unsupported
 */
public int getSeconds(){
  return getPeriodType().getIndexedField(this,PeriodType.SECOND_INDEX);
}
