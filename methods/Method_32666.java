/** 
 * Converts this period in seconds to a period in minutes assuming a 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all minutes are 60 seconds long. This may not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules.
 * @return a period representing the number of minutes for this number of seconds
 */
public Minutes toStandardMinutes(){
  return Minutes.minutes(getValue() / DateTimeConstants.SECONDS_PER_MINUTE);
}
