/** 
 * Converts this period in days to a period in weeks assuming a 7 day week. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days long. This may not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of weeks for this number of days
 */
public Weeks toStandardWeeks(){
  return Weeks.weeks(getValue() / DateTimeConstants.DAYS_PER_WEEK);
}
