/** 
 * Create a KeyMatcher that matches Keys that equal the given key. 
 */
public static <U extends Key<?>>KeyMatcher<U> keyEquals(U compareTo){
  return new KeyMatcher<U>(compareTo);
}
