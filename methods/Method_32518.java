/** 
 * Converts this period in minutes to a period in days assuming a 24 hour day and 60 minute hour. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all days are 24 hours long and all hours are 60 minutes long. This is not true when daylight savings is considered and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of whole days for this number of minutes
 */
public Days toStandardDays(){
  return Days.days(getValue() / DateTimeConstants.MINUTES_PER_DAY);
}
