/** 
 * Asserts that two objects do not refer to the same object. If they do refer to the same object an AssertionFailedError is thrown with the given message.
 */
static public void assertNotSame(String message,Object expected,Object actual){
  if (expected == actual) {
    failSame(message);
  }
}
