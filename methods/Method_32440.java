/** 
 * Obtains an  {@code Instant} set to the seconds from 1970-01-01T00:00:00Z.
 * @param epochSecond  the seconds from 1970-01-01T00:00:00Z
 * @throws ArithmeticException if the new instant exceeds the capacity of a long
 * @since 2.10
 */
public static Instant ofEpochSecond(long epochSecond){
  return new Instant(FieldUtils.safeMultiply(epochSecond,1000));
}
