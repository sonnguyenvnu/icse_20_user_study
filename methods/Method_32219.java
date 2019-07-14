/** 
 * Multiply two values throwing an exception if overflow occurs.
 * @param val1  the first value
 * @param val2  the second value
 * @return the new total
 * @throws ArithmeticException if the value is too big or too small
 * @since 1.2
 */
public static int safeMultiply(int val1,int val2){
  long total=(long)val1 * (long)val2;
  if (total < Integer.MIN_VALUE || total > Integer.MAX_VALUE) {
    throw new ArithmeticException("Multiplication overflows an int: " + val1 + " * " + val2);
  }
  return (int)total;
}
