/** 
 * Sets all the fields in one go from two instants representing an interval. <p> The chronology of the start instant is used, unless that is null when the chronology of the end instant is used instead.
 * @param start  the start instant, null means now
 * @param end  the end instant, null means now
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(ReadableInstant start,ReadableInstant end){
  if (start == end) {
    setPeriod(0L);
  }
 else {
    long startMillis=DateTimeUtils.getInstantMillis(start);
    long endMillis=DateTimeUtils.getInstantMillis(end);
    Chronology chrono=DateTimeUtils.getIntervalChronology(start,end);
    setPeriod(startMillis,endMillis,chrono);
  }
}
