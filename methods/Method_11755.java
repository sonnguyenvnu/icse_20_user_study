/** 
 * Asserts that two objects do not refer to the same object. If they do refer to the same object, an  {@link AssertionError} is thrown with thegiven message.
 * @param message the identifying message for the {@link AssertionError} (<code>null</code>okay)
 * @param unexpected the object you don't expect
 * @param actual the object to compare to <code>unexpected</code>
 */
public static void assertNotSame(String message,Object unexpected,Object actual){
  if (unexpected == actual) {
    failSame(message);
  }
}
