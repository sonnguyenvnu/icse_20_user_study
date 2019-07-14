/** 
 * Converts this period in days to a duration in milliseconds assuming a 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert from a period to a duration. However to achieve this it makes the assumption that all days are 24 hours long, all hours are 60 minutes and all minutes are 60 seconds. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a duration equivalent to this number of days
 */
public Duration toStandardDuration(){
  long days=getValue();
  return new Duration(days * DateTimeConstants.MILLIS_PER_DAY);
}
