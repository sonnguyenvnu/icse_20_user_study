/** 
 * Returns the unsigned quotient of dividing the first argument by the second where each argument and the result is interpreted as an unsigned value. <p>When either argument is negative (i.e. a  {@code uint64_t} value higher than {@code 0x8000_0000_0000_0000L}), this method uses bit twiddling to implement the division. The JDK implementation uses  {@link BigInteger} for this case, which has a negative impact on performance.</p>
 * @param dividend the value to be divided
 * @param divisor  the value doing the dividing
 * @return the unsigned quotient of the first argument divided by the second argument
 */
public static long mathDivideUnsigned(long dividend,long divisor){
  if (0L <= divisor) {
    if (0L <= dividend) {
      return dividend / divisor;
    }
 else {
      return udivdi3(dividend,divisor);
    }
  }
 else {
    return Long.compareUnsigned(dividend,divisor) < 0 ? 0L : 1L;
  }
}
