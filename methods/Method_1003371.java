/** 
 * Get or create a timestamp value for the given timestamp.
 * @param timestamp the timestamp
 * @return the value
 */
public static ValueTimestamp get(Timestamp timestamp){
  long ms=timestamp.getTime();
  long nanos=timestamp.getNanos() % 1_000_000;
  ms+=DateTimeUtils.getTimeZoneOffset(ms);
  long dateValue=DateTimeUtils.dateValueFromLocalMillis(ms);
  nanos+=DateTimeUtils.nanosFromLocalMillis(ms);
  return fromDateValueAndNanos(dateValue,nanos);
}
