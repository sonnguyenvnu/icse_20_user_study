/** 
 * Gets the total millisecond duration of this period relative to an end instant. <p> This method subtracts the period from the specified instant in order to calculate the duration. <p> An instant must be supplied as the duration of a period varies. For example, a period of 1 month could vary between the equivalent of 28 and 31 days in milliseconds due to different length months. Similarly, a day can vary at Daylight Savings cutover, typically between 23 and 25 hours.
 * @param endInstant  the instant to subtract the period from, thus obtaining the duration
 * @return the total length of the period as a duration relative to the end instant
 * @throws ArithmeticException if the millis exceeds the capacity of the duration
 */
public Duration toDurationTo(ReadableInstant endInstant){
  long endMillis=DateTimeUtils.getInstantMillis(endInstant);
  Chronology chrono=DateTimeUtils.getInstantChronology(endInstant);
  long startMillis=chrono.add(this,endMillis,-1);
  return new Duration(startMillis,endMillis);
}
