/** 
 * Converts this period in weeks to a duration in milliweeks assuming a 7 day week, 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert from a period to a duration. However to achieve this it makes the assumption that all weeks are 7 days long, all days are 24 hours long, all hours are 60 minutes long and all minutes are 60 seconds long. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a duration equivalent to this number of weeks
 */
public Duration toStandardDuration(){
  long weeks=getValue();
  return new Duration(weeks * DateTimeConstants.MILLIS_PER_WEEK);
}
