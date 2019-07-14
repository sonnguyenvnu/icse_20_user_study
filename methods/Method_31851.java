/** 
 * Gets the total millisecond duration of this period relative to a start instant. <p> This method adds the period to the specified instant in order to calculate the duration. <p> An instant must be supplied as the duration of a period varies. For example, a period of 1 month could vary between the equivalent of 28 and 31 days in milliseconds due to different length months. Similarly, a day can vary at Daylight Savings cutover, typically between 23 and 25 hours.
 * @param startInstant  the instant to add the period to, thus obtaining the duration
 * @return the total length of the period as a duration relative to the start instant
 * @throws ArithmeticException if the millis exceeds the capacity of the duration
 */
public Duration toDurationFrom(ReadableInstant startInstant){
  long startMillis=DateTimeUtils.getInstantMillis(startInstant);
  Chronology chrono=DateTimeUtils.getInstantChronology(startInstant);
  long endMillis=chrono.add(this,startMillis,1);
  return new Duration(startMillis,endMillis);
}
