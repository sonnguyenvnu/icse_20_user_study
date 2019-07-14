/** 
 * Converts this period in seconds to a period in days assuming a 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all days are 24 hours long, all hours are 60 minutes long and all minutes are 60 seconds long. This is not true when daylight savings is considered and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of days for this number of seconds
 */
public Days toStandardDays(){
  return Days.days(getValue() / DateTimeConstants.SECONDS_PER_DAY);
}
