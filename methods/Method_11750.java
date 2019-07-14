/** 
 * Asserts that two objects are <b>not</b> equals. If they are, an {@link AssertionError} without a message is thrown. If<code>unexpected</code> and <code>actual</code> are <code>null</code>, they are considered equal.
 * @param unexpected unexpected value to check
 * @param actual the value to check against <code>unexpected</code>
 */
public static void assertNotEquals(Object unexpected,Object actual){
  assertNotEquals(null,unexpected,actual);
}
