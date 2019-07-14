/** 
 * Converts this period in minutes to a period in seconds assuming a 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all minutes are 60 seconds long. This may not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of seconds for this number of minutes
 * @throws ArithmeticException if the number of seconds is too large to be represented
 */
public Seconds toStandardSeconds(){
  return Seconds.seconds(FieldUtils.safeMultiply(getValue(),DateTimeConstants.SECONDS_PER_MINUTE));
}
