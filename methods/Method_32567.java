/** 
 * Sets this interval from two instants, replacing the chronology with that from the start instant.
 * @param start  the start of the time interval
 * @param end  the start of the time interval
 * @throws IllegalArgumentException if the end is before the start
 */
public void setInterval(ReadableInstant start,ReadableInstant end){
  if (start == null && end == null) {
    long now=DateTimeUtils.currentTimeMillis();
    setInterval(now,now);
  }
 else {
    long startMillis=DateTimeUtils.getInstantMillis(start);
    long endMillis=DateTimeUtils.getInstantMillis(end);
    Chronology chrono=DateTimeUtils.getInstantChronology(start);
    super.setInterval(startMillis,endMillis,chrono);
  }
}
