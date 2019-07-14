/** 
 * Add two values throwing an exception if overflow occurs.
 * @param val1  the first value
 * @param val2  the second value
 * @return the new total
 * @throws ArithmeticException if the value is too big or too small
 */
public static long safeAdd(long val1,long val2){
  long sum=val1 + val2;
  if ((val1 ^ sum) < 0 && (val1 ^ val2) >= 0) {
    throw new ArithmeticException("The calculation caused an overflow: " + val1 + " + " + val2);
  }
  return sum;
}
