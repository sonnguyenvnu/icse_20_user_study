/** 
 * Is this time interval entirely after the specified interval. <p> Intervals are inclusive of the start instant and exclusive of the end. Only the end time of the specified interval is used in the comparison.
 * @param interval  the interval to compare to, null means now
 * @return true if this time interval is after the interval specified
 */
public boolean isAfter(ReadableInterval interval){
  long endMillis;
  if (interval == null) {
    endMillis=DateTimeUtils.currentTimeMillis();
  }
 else {
    endMillis=interval.getEndMillis();
  }
  return (getStartMillis() >= endMillis);
}
