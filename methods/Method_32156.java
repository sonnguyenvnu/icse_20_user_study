/** 
 * Converts this period in days to a period in hours assuming a 24 hour day. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all days are 24 hours long. This is not true when daylight savings is considered and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of hours for this number of days
 * @throws ArithmeticException if the number of hours is too large to be represented
 */
public Hours toStandardHours(){
  return Hours.hours(FieldUtils.safeMultiply(getValue(),DateTimeConstants.HOURS_PER_DAY));
}
