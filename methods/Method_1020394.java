/** 
 * Creates a new timestamp from given seconds and nanoseconds.
 * @param seconds Represents seconds of UTC time since Unix epoch 1970-01-01T00:00:00Z. Must befrom from 0001-01-01T00:00:00Z to 9999-12-31T23:59:59Z inclusive.
 * @param nanos Non-negative fractions of a second at nanosecond resolution. Negative secondvalues with fractions must still have non-negative nanos values that count forward in time. Must be from 0 to 999,999,999 inclusive.
 * @return new {@code Timestamp} with specified fields.
 * @throws IllegalArgumentException if the arguments are out of range.
 * @since 0.5
 */
public static Timestamp create(long seconds,int nanos){
  if (seconds < -MAX_SECONDS) {
    throw new IllegalArgumentException("'seconds' is less than minimum (" + -MAX_SECONDS + "): " + seconds);
  }
  if (seconds > MAX_SECONDS) {
    throw new IllegalArgumentException("'seconds' is greater than maximum (" + MAX_SECONDS + "): " + seconds);
  }
  if (nanos < 0) {
    throw new IllegalArgumentException("'nanos' is less than zero: " + nanos);
  }
  if (nanos > MAX_NANOS) {
    throw new IllegalArgumentException("'nanos' is greater than maximum (" + MAX_NANOS + "): " + nanos);
  }
  return new AutoValue_Timestamp(seconds,nanos);
}
