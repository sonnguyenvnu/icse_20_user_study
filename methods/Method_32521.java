/** 
 * Converts this period in minutes to a duration in milliseconds assuming a 60 second minute. <p> This method allows you to convert from a period to a duration. However to achieve this it makes the assumption that all minutes are 60 seconds long. This might not be true for an unusual chronology, for example one that takes leap seconds into account. However, the method is included as it is a useful operation for many applications and business rules.
 * @return a duration equivalent to this number of minutes
 */
public Duration toStandardDuration(){
  long minutes=getValue();
  return new Duration(minutes * DateTimeConstants.MILLIS_PER_MINUTE);
}
