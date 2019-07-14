/** 
 * Converts this period in hours to a period in days assuming a 24 hour day. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all days are 24 hours long. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of whole days for this number of hours
 */
public Days toStandardDays(){
  return Days.days(getValue() / DateTimeConstants.HOURS_PER_DAY);
}
