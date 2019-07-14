/** 
 * Gets the length of this duration in days assuming that there are the standard number of milliseconds in a day. <p> This method assumes that there are 24 hours in a day, 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in a second. This will be true for most days, however days with Daylight Savings changes will not have 24 hours, so use this method with care. <p> This returns <code>getMillis() / MILLIS_PER_DAY</code>. The result is an integer division, thus excess milliseconds are truncated.
 * @return the length of the duration in standard days
 * @since 2.0
 */
public long getStandardDays(){
  return getMillis() / DateTimeConstants.MILLIS_PER_DAY;
}
