/** 
 * Is this instant strictly after the instant passed in comparing solely by millisecond.
 * @param instant  an instant to check against, null means now
 * @return true if the instant is strictly after the instant passed in
 */
public boolean isAfter(ReadableInstant instant){
  long instantMillis=DateTimeUtils.getInstantMillis(instant);
  return isAfter(instantMillis);
}
