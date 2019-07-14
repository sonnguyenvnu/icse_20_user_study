/** 
 * Calculates the number of whole units between the two specified datetimes.
 * @param start  the start instant, validated to not be null
 * @param end  the end instant, validated to not be null
 * @param field  the field type to use, must not be null
 * @return the period
 * @throws IllegalArgumentException if the instants are null or invalid
 */
protected static int between(ReadableInstant start,ReadableInstant end,DurationFieldType field){
  if (start == null || end == null) {
    throw new IllegalArgumentException("ReadableInstant objects must not be null");
  }
  Chronology chrono=DateTimeUtils.getInstantChronology(start);
  int amount=field.getField(chrono).getDifference(end.getMillis(),start.getMillis());
  return amount;
}
