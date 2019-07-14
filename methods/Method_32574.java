/** 
 * Sets the period of this time interval, preserving the start instant and using the ISOChronology in the default zone for calculations.
 * @param period  new period for interval, null means zero length
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the end instant exceeds the capacity of a long
 */
public void setPeriodAfterStart(ReadablePeriod period){
  if (period == null) {
    setEndMillis(getStartMillis());
  }
 else {
    setEndMillis(getChronology().add(period,getStartMillis(),1));
  }
}
