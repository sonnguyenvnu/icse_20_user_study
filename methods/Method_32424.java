/** 
 * Converts this period in hours to a period in weeks assuming a 7 day week and 24 hour day. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days long and all days are 24 hours long. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of whole weeks for this number of hours
 */
public Weeks toStandardWeeks(){
  return Weeks.weeks(getValue() / DateTimeConstants.HOURS_PER_WEEK);
}
