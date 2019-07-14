/** 
 * Gets the millis field part of the period.
 * @return the number of millis in the period, zero if unsupported
 */
public int getMillis(){
  return getPeriodType().getIndexedField(this,PeriodType.MILLI_INDEX);
}
