/** 
 * Create a GroupMatcher that matches groups equaling the given string.
 */
public static <T extends Key<T>>GroupMatcher<T> groupEquals(String compareTo){
  return new GroupMatcher<T>(compareTo,StringOperatorName.EQUALS);
}
