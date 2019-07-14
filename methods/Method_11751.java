/** 
 * Asserts that two longs are <b>not</b> equals. If they are, an {@link AssertionError} without a message is thrown.
 * @param unexpected unexpected value to check
 * @param actual the value to check against <code>unexpected</code>
 */
public static void assertNotEquals(long unexpected,long actual){
  assertNotEquals(null,unexpected,actual);
}
