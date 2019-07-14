/** 
 * Sets all the fields in one go from an interval using the ISO chronology and dividing the fields using the period type.
 * @param interval  the interval to set, null means zero length
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(ReadableInterval interval){
  if (interval == null) {
    setPeriod(0L);
  }
 else {
    Chronology chrono=DateTimeUtils.getChronology(interval.getChronology());
    setPeriod(interval.getStartMillis(),interval.getEndMillis(),chrono);
  }
}
