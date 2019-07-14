/** 
 * Divides the dividend by divisor. Rounding of result occurs as per the roundingMode.
 * @param dividend  the dividend
 * @param divisor  the divisor
 * @param roundingMode  the desired rounding mode
 * @return the division result as per the specified rounding mode
 * @throws ArithmeticException if the operation overflows or the divisor is zero
 */
public static long safeDivide(long dividend,long divisor,RoundingMode roundingMode){
  if (dividend == Long.MIN_VALUE && divisor == -1L) {
    throw new ArithmeticException("Multiplication overflows a long: " + dividend + " / " + divisor);
  }
  BigDecimal dividendBigDecimal=new BigDecimal(dividend);
  BigDecimal divisorBigDecimal=new BigDecimal(divisor);
  return dividendBigDecimal.divide(divisorBigDecimal,roundingMode).longValue();
}
