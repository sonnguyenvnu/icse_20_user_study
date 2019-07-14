/** 
 * Converts this period in minutes to a period in hours assuming a 60 minute hour. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all hours are 60 minutes long. This may not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of hours for this number of minutes
 */
public Hours toStandardHours(){
  return Hours.hours(getValue() / DateTimeConstants.MINUTES_PER_HOUR);
}
