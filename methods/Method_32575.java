/** 
 * Sets the period of this time interval, preserving the end instant and using the ISOChronology in the default zone for calculations.
 * @param period  new period for interval, null means zero length
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the start instant exceeds the capacity of a long
 */
public void setPeriodBeforeEnd(ReadablePeriod period){
  if (period == null) {
    setStartMillis(getEndMillis());
  }
 else {
    setStartMillis(getChronology().add(period,getEndMillis(),-1));
  }
}
