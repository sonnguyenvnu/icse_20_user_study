/** 
 * Asserts that two objects do not refer to the same object. If they do refer to the same object, an  {@link AssertionError} without a message isthrown.
 * @param unexpected the object you don't expect
 * @param actual the object to compare to <code>unexpected</code>
 */
public static void assertNotSame(Object unexpected,Object actual){
  assertNotSame(null,unexpected,actual);
}
