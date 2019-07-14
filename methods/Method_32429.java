/** 
 * Returns a new instance with the hours divided by the specified divisor. The calculation uses integer division, thus 3 divided by 2 is 1. <p> This instance is immutable and unaffected by this method call.
 * @param divisor  the amount to divide by, may be negative
 * @return the new period divided by the specified divisor
 * @throws ArithmeticException if the divisor is zero
 */
public Hours dividedBy(int divisor){
  if (divisor == 1) {
    return this;
  }
  return Hours.hours(getValue() / divisor);
}
