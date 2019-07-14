/** 
 * Asserts that two objects are equal. If they are not, an {@link AssertionError} is thrown with the given message. If<code>expected</code> and <code>actual</code> are <code>null</code>, they are considered equal.
 * @param message the identifying message for the {@link AssertionError} (<code>null</code>okay)
 * @param expected expected value
 * @param actual actual value
 */
public static void assertEquals(String message,Object expected,Object actual){
  if (equalsRegardingNull(expected,actual)) {
    return;
  }
  if (expected instanceof String && actual instanceof String) {
    String cleanMessage=message == null ? "" : message;
    throw new ComparisonFailure(cleanMessage,(String)expected,(String)actual);
  }
 else {
    failNotEquals(message,expected,actual);
  }
}
