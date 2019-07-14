/** 
 * Casts to an int throwing an exception if overflow occurs.
 * @param value  the value
 * @return the value as an int
 * @throws ArithmeticException if the value is too big or too small
 */
public static int safeToInt(long value){
  if (Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE) {
    return (int)value;
  }
  throw new ArithmeticException("Value cannot fit in an int: " + value);
}
