/** 
 * Asserts that an object is null. If it is not, an  {@link AssertionError}is thrown with the given message.
 * @param message the identifying message for the {@link AssertionError} (<code>null</code>okay)
 * @param object Object to check or <code>null</code>
 */
public static void assertNull(String message,Object object){
  if (object == null) {
    return;
  }
  failNotNull(message,object);
}
