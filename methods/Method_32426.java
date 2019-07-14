/** 
 * Converts this period in hours to a duration in milliseconds assuming a 60 minute hour and 60 second minute. <p> This method allows you to convert from a period to a duration. However to achieve this it makes the assumption that all hours are 60 minutes and all minutes are 60 seconds. This might not be true for an unusual chronology, for example one that takes leap seconds into account. However, the method is included as it is a useful operation for many applications and business rules.
 * @return a duration equivalent to this number of hours
 */
public Duration toStandardDuration(){
  long hours=getValue();
  return new Duration(hours * DateTimeConstants.MILLIS_PER_HOUR);
}
