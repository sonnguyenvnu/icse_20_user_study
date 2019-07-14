/** 
 * Returns the difference of  {@code a} and {@code b} unless it would overflow or underflow inwhich case  {@code Long.MAX_VALUE} or {@code Long.MIN_VALUE} is returned, respectively.
 */
@SuppressWarnings("ShortCircuitBoolean") private static long saturatedSubtract(long a,long b){
  long naiveDifference=a - b;
  if ((a ^ b) >= 0 | (a ^ naiveDifference) >= 0) {
    return naiveDifference;
  }
  return Long.MAX_VALUE + ((naiveDifference >>> (Long.SIZE - 1)) ^ 1);
}
