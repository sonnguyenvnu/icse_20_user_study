/** 
 * Gets the length of this duration in seconds assuming that there are the standard number of milliseconds in a second. <p> This method assumes that there are 1000 milliseconds in a second. All currently supplied chronologies use this definition. <p> This returns <code>getMillis() / 1000</code>. The result is an integer division, so 2999 millis returns 2 seconds.
 * @return the length of the duration in standard seconds
 * @since 1.6
 */
public long getStandardSeconds(){
  return getMillis() / DateTimeConstants.MILLIS_PER_SECOND;
}
