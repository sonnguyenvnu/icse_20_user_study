/** 
 * Rounds the specified integer  {@code value} up to the next power-of-twonumber. The returned value will be equal to  {@code value} if it alreadyis a power-of-two number.
 * @param value the value to round-up. Must be a number between {@code 1} and<code>1 &lt;&lt; 30</code>.
 * @return the power-of-two rounded value
 */
public static int mathRoundPoT(int value){
  return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
}
