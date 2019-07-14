/** 
 * Asserts that a condition is true
 * @param condition The condition under test
 * @param message The message for any exception
 */
public static void isTrue(boolean condition,String message){
  if (!condition) {
    throw new IllegalArgumentException(message);
  }
}
