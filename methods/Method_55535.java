/** 
 * Returns true if the specified integer  {@code value} is a power-of-twonumber.
 * @param value the value to test
 * @return true if the value if a power-of-two number.
 */
public static boolean mathIsPoT(int value){
  return Integer.bitCount(value) == 1;
}
