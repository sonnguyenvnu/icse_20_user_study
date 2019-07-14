/** 
 * Gets the length of this duration in minutes assuming that there are the standard number of milliseconds in a minute. <p> This method assumes that there are 60 seconds in a minute and 1000 milliseconds in a second. All currently supplied chronologies use this definition. <p> This returns <code>getMillis() / MILLIS_PER_MINUTE</code>. The result is an integer division, thus excess milliseconds are truncated.
 * @return the length of the duration in standard minutes
 * @since 2.0
 */
public long getStandardMinutes(){
  return getMillis() / DateTimeConstants.MILLIS_PER_MINUTE;
}
