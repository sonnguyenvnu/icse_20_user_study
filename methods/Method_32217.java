/** 
 * Negates the input throwing an exception if it can't negate it.
 * @param value  the value to negate
 * @return the negated value
 * @throws ArithmeticException if the value is Integer.MIN_VALUE
 * @since 1.1
 */
public static int safeNegate(int value){
  if (value == Integer.MIN_VALUE) {
    throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");
  }
  return -value;
}
