/** 
 * Converts this period in weeks to a period in hours assuming a 7 day week and 24 hour day. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days long and all days are 24 hours long. This is not true when daylight savings is considered and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of hours for this number of weeks
 * @throws ArithmeticException if the number of hours is too large to be represented
 */
public Hours toStandardHours(){
  return Hours.hours(FieldUtils.safeMultiply(getValue(),DateTimeConstants.HOURS_PER_WEEK));
}
