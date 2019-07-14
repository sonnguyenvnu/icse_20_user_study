/** 
 * Converts this period in weeks to a period in seconds assuming a 7 day week, 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days long, all days are 24 hours long, all hours are 60 minutes long and all minutes are 60 seconds long. This is not true when daylight savings is considered and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of seconds for this number of weeks
 * @throws ArithmeticException if the number of seconds is too large to be represented
 */
public Seconds toStandardSeconds(){
  return Seconds.seconds(FieldUtils.safeMultiply(getValue(),DateTimeConstants.SECONDS_PER_WEEK));
}
