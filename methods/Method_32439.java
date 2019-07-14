/** 
 * Obtains an  {@code Instant} set to the milliseconds from 1970-01-01T00:00:00Z.
 * @param epochMilli  the milliseconds from 1970-01-01T00:00:00Z
 * @since 2.10
 */
public static Instant ofEpochMilli(long epochMilli){
  return new Instant(epochMilli);
}
