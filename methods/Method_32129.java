/** 
 * Gets the millisecond instant from the specified instant object handling null. <p> If the instant object is <code>null</code>, the  {@link #currentTimeMillis()}will be returned. Otherwise, the millis from the object are returned.
 * @param instant  the instant to examine, null means now
 * @return the time in milliseconds from 1970-01-01T00:00:00Z
 */
public static final long getInstantMillis(ReadableInstant instant){
  if (instant == null) {
    return DateTimeUtils.currentTimeMillis();
  }
  return instant.getMillis();
}
