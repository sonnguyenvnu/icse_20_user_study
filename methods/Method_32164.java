/** 
 * Gets the length of this duration in hours assuming that there are the standard number of milliseconds in an hour. <p> This method assumes that there are 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in a second. All currently supplied chronologies use this definition. <p> This returns <code>getMillis() / MILLIS_PER_HOUR</code>. The result is an integer division, thus excess milliseconds are truncated.
 * @return the length of the duration in standard hours
 * @since 2.0
 */
public long getStandardHours(){
  return getMillis() / DateTimeConstants.MILLIS_PER_HOUR;
}
